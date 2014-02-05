package com.lisilab.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBAsyncClient;
import com.lisilab.aws.S3StorageManager;
import com.lisilab.its.model.Student;
import com.spaceprogram.simplejpa.EntityManagerFactoryImpl;

public class DictionaryDAO {

	private static Map<String, String> properties = new HashMap<String, String>();
	private static AWSCredentials credentials = new BasicAWSCredentials(
			S3StorageManager.getKey(), S3StorageManager.getSecret());

	// private static AmazonSimpleDBAsyncClient client = new
	// AmazonSimpleDBAsyncClient();

	static {
		properties.put("accessKey", credentials.getAWSAccessKeyId());
		properties.put("secretKey", credentials.getAWSSecretKey());
//		properties.put("lobBucketName", S3StorageManager.getKey().toLowerCase()
//				+ "-lisilab-lob");
	}

	private static EntityManagerFactoryImpl factory = new EntityManagerFactoryImpl(
			"LISILab", properties);

	public Student getStudent(String name) {
		EntityManager em = null;

		try {
			em = factory.createEntityManager();
			Student student = em.find(Student.class, name);
			return student;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public void saveStudent(Student student) {
		EntityManager em = null;

		try {
			em = factory.createEntityManager();
			em.persist(student);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void deleteStudent(Student student) {
		EntityManager em = null;

		try {
			em = factory.createEntityManager();
			em.remove(student);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}
