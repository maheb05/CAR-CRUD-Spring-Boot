package com.carapi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "car_table")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CAR_ID")
	private Long carId;
	
	@Column(name = "CAR_NAME")
	private String carName;
	
	@Column(name = "CAR_MODEL")
	private String carModel;
	
	@Column(name = "MILEAGE")
	private String mileage;
	
	@Column(name = "REVIEW")
	private String review;
	
	public Car() {
		
	}
	
	public Car(Long carId, String carName, String carModel, String mileage, String review) {
		super();
		this.carId = carId;
		this.carName = carName;
		this.carModel = carModel;
		this.mileage = mileage;
		this.review = review;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carName=" + carName + ", carModel=" + carModel + ", mileage=" + mileage
				+ ", review=" + review + "]";
	}
	
	
}
