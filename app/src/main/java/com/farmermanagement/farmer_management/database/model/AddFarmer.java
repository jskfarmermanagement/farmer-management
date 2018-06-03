package com.farmermanagement.farmer_management.database.model;

public class AddFarmer {

    public static final String TABLE_NAME = "farmers";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FARMER_NAME = "farmer_name";
    public static final String COLUMN_FATHER_NAME = "father_name";
    public static final String COLUMN_VILLAGE = "village";
    public static final String COLUMN_MOBILE = "mobile";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String farmerName;
    private String fatherName;
    private String village;
    private String mobile;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_FARMER_NAME + " TEXT,"
                    + COLUMN_FATHER_NAME + " TEXT,"
                    + COLUMN_VILLAGE + " TEXT,"
                    + COLUMN_MOBILE + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public AddFarmer() {
    }

    public AddFarmer(int id, String farmerName, String fatherName, String village, String mobile, String timestamp) {
        this.id = id;
        this.farmerName = farmerName;
        this.fatherName = fatherName;
        this.village = village;
        this.mobile = mobile;
        this.timestamp = timestamp;
    }


    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
