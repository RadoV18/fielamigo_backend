package com.fielamigo.app.FielAmigo;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.s3.AmazonS3;
import com.fielamigo.app.FielAmigo.service.FileStorageService;
import com.fielamigo.app.FielAmigo.service.MailService;
import com.fielamigo.app.FielAmigo.service.MailhogMailServiceImpl;
import com.fielamigo.app.FielAmigo.service.S3FileStorageServiceImpl;

@SpringBootApplication
@MapperScan("com.fielamigo.app.FielAmigo.dao")
public class FielAmigoApplication {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		return sessionFactory.getObject();
	}

	// implementations
	@Bean
	public FileStorageService fileStorageService() {
		return new S3FileStorageServiceImpl();
	}

	@Bean
	public MailService mailService() {
		return new MailhogMailServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(FielAmigoApplication.class, args);
	}

}
