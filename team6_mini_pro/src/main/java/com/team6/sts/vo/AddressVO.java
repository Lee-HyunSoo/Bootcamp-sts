package com.team6.sts.vo;

public class AddressVO {
	private String zip_num;
	private String sido;
	private String gugun;
	private String dong;
	private String zip_code;
	private String bunji;

	public AddressVO() {

	}

	public String getzipNum() {
		return zip_num;
	}

	public void setzipNum(String zipNum) {
		this.zip_num = zipNum;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getzipCode() {
		return zip_code;
	}

	public void setzipCode(String zipCode) {
		this.zip_code = zipCode;
	}

	public String getBunji() {
		return bunji;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}

	@Override
	public String toString() {
		return "AddressVO [zipNum=" + zip_num + ", sido=" + sido + ", gugun=" + gugun + ", dong=" + dong + ", zipCode="
				+ zip_code + ", bunji=" + bunji + "]";
	}
	
	
}
