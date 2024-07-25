package raintree.testAssignment.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

public class Patient implements PatientRecord{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, nullable = false)
    private Integer _id;
    @Column(length = 11)
    private String pn;
    @Column(length = 15)
    private String first;
    @Column(length = 25)
    private String last;
    @Temporal(TemporalType.DATE)
    private Date dob;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private List<Insurance> insuranceList;

    public Patient() {}

   public Patient (Integer id, String pn, String first, String last, Date dob){
        this._id = id;
        this.pn = pn;
        this.first = first;
        this.last = last;
        this.dob = dob;

    }


    @Override
    public Integer getId() {
        return this._id;
    }

    @Override
    public String getPatientNumber() {
        return this.pn;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getFullName(){
        return this.first + " " + this.last;
    }
    public List<Insurance> getInsuranceList() {
        return this.insuranceList;
    }

    public void addInsurance(Insurance insurance){
        this.insuranceList = new ArrayList<>();
        this.insuranceList.add(insurance);
    }

    public void setInsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }

    public void displayPatientInsuranceInfo(String date){

        try {
            for(Insurance insurance : getInsuranceList()){
                boolean isValid = insurance.isValidDate(date);

                    System.out.println( getPatientNumber() + ", " +
                            getFullName() + ", " +
                            insurance.getIname() + ", " + (isValid ? "YES" : "NO"));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong with displaying insurance info!");
        }

    }
}
