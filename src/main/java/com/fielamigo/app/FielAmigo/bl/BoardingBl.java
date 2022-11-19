package com.fielamigo.app.FielAmigo.bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.AvailableBoardingCaregiverDao;
import com.fielamigo.app.FielAmigo.dto.CaregiverBoardingReqDto;
import com.fielamigo.app.FielAmigo.entity.AvailableCaregiver;
import com.fielamigo.app.FielAmigo.utils.Pair;

@Service
public class BoardingBl {

    private AvailableBoardingCaregiverDao availableBoardingCaregiverDao;

    public BoardingBl(AvailableBoardingCaregiverDao availableCaregiverDao) {
        this.availableBoardingCaregiverDao = availableCaregiverDao;
    }

    /**
     * Method to get all the caregivers that are available to board a pet.
     * @param req the request dto.
     */
    public List<Integer> getAvailableCaregivers(CaregiverBoardingReqDto req) {
        System.out.println("Boarding search request...");
        System.out.println("\tFrom " + req.getStartingDate() + " to " + req.getEndingDate());
        List<Integer> result = new ArrayList<>();

        // calculate the nights between the starting and ending date.
        int nights = (int) ((req.getEndingDate().getTime() - req.getStartingDate().getTime())
            / (1000 * 60 * 60 * 24));

        int dogCount = req.getDogCount();

        // get all the possible caregivers given the nights, the city and the dog count
        List<Pair<Integer, Integer>> possibleCaregivers = availableBoardingCaregiverDao
            .getPossibleCaregivers(dogCount, nights, req.getCityId());
        System.out.println("Possible caregivers: " + possibleCaregivers.size());
        for(Pair<Integer, Integer> p : possibleCaregivers) {
            System.out.println("\tCaregiver " + p.getFirst());
        }

        // HashMap to store the maximum number of dogs boarded by each caregiver.
        // The key is the caregiver id and the value is the maximum number of dogs.
        HashMap<Integer, Integer> maxDogsPerCaregiver = new HashMap<>();

        for (Pair<Integer, Integer> caregiver : possibleCaregivers) {
            // assert all the caregivers are available to board the pets.
            result.add(caregiver.getFirst());
            maxDogsPerCaregiver.put(caregiver.getFirst(), caregiver.getSecond());
        }

        // get the possible caregivers that have bookings in the selected dates.
        List<AvailableCaregiver> bookedCaregivers =
            availableBoardingCaregiverDao.getBookedCaregivers(req.getStartingDate(),
                req.getEndingDate(), req.getCityId(), nights);

        // HashMap to store the number of dogs boarded by each caregiver per day.
        // The key is the date and the value is a hashmap with the caregiver id and the
        // number of dogs boarded.
        HashMap<Long, HashMap<Integer, Integer>> dogsPerDay = new HashMap<>();

        // loop through the available caregivers.
        for (AvailableCaregiver avCrg : bookedCaregivers) {

            Date starting = avCrg.getStartingDate();
            Date ending = avCrg.getEndingDate();

            // dogs on the date from the database
            int dogsOnDate = avCrg.getDogCount();

            // for loop from starting to ending date
            for (Date date = starting; date.before(ending); date = new Date(date.getTime() + 86400000)) {
                // if the date has already been added to the hashmap
                if (dogsPerDay.containsKey(date.getTime())) {
                    // get the hashmap with the caregiver id and the number of dogs boarded
                    HashMap<Integer, Integer> dogsPerCaregiver = dogsPerDay.get(date.getTime());

                    // if the caregiver has already been added to the hashmap
                    if(dogsPerCaregiver.containsKey(avCrg.getCaregiverId())) {
                        
                        // get current number of dogs
                        int currentDogs = dogsPerDay.get(date.getTime()).get(avCrg.getCaregiverId());
                        int total = currentDogs + dogsOnDate;

                        // add the new number of dogs
                        dogsPerDay.get(date.getTime()).put(avCrg.getCaregiverId(), total);

                    } else {
                        // add the caregiver id and the number of dogs boarded on the date
                        dogsPerCaregiver.put(avCrg.getCaregiverId(), dogsOnDate);
                    }

                } else {
                    // create a new hashmap with the caregiver id and the number of dogs boarded
                    HashMap<Integer, Integer> dogsPerCaregiver = new HashMap<>();
                    dogsPerCaregiver.put(avCrg.getCaregiverId(), dogsOnDate);

                    // add the new hashmap to the dogsPerDay hashmap
                    dogsPerDay.put(date.getTime(), dogsPerCaregiver);
                }
            }
        }

        // loop through the dates selected by the user
        for(Date date = req.getStartingDate(); date.before(req.getEndingDate()); date = new Date(date.getTime() + 86400000)) {
            // if the date is in the dogsPerDay hashmap
            if(dogsPerDay.containsKey(date.getTime())) {
                System.out.println("++++++++++++++++++++++++++++++++++++");
                System.out.println("On Date " + date);
                // get the hashmap with the caregiver id and the number of dogs boarded
                HashMap<Integer, Integer> dogsPerCaregiver = dogsPerDay.get(date.getTime());

                // loop through the caregivers
                for (Integer caregiverId : dogsPerCaregiver.keySet()) {
                    // skip the caregiver if it has already been discarded
                    if(!result.contains(caregiverId)) {
                        continue;
                    }

                    System.out.println("Caregiver " + caregiverId + " has " + dogsPerCaregiver.get(caregiverId) + " dogs booked out of " + maxDogsPerCaregiver.get(caregiverId));
                    // get the number of dogs boarded by the caregiver
                    int bookedDogs = dogsPerCaregiver.get(caregiverId);

                    // available spaces on the day
                    int availableSpaces = maxDogsPerCaregiver.get(caregiverId) - bookedDogs;
                    System.out.println("\t" + availableSpaces + " available spaces with " + dogCount + " dogs to board");
                    // if the number of dogs requested is greater than the available spaces
                    if (req.getDogCount() > availableSpaces) {
                        // discard the caregiver from the result
                        System.out.println("\tCaregiver " + caregiverId + " is not available...");
                        result.remove(caregiverId);
                    }
                }

            } // else no caregivers are booked on that date
        }

        return result;
    }
}
