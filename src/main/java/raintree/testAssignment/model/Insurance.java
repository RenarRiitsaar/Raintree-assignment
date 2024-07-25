package raintree.testAssignment.model;

import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Insurance implements PatientRecord{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length= 10)
    private Integer _id;
    @Column(length = 40)
    private String iname;
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Temporal(TemporalType.DATE)
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public Insurance() {}

    public Insurance(Integer _id, String iname, Date fromDate, Date toDate){
        this._id = _id;
        this.iname = iname;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public boolean isValidDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
        boolean isValid = false;
        try {
            Date formattedDate = sdf.parse(date);

            if(toDate == null &&fromDate.before(formattedDate) ||
                    toDate == null && fromDate.equals(formattedDate)){
                isValid = true;

            }else if (toDate != null && fromDate.before(formattedDate) && toDate.after(formattedDate)) {
                isValid = true;

            }else if(toDate != null && fromDate.before(formattedDate) && toDate.equals(formattedDate)){
                isValid = true;

            }else if(toDate != null && fromDate.equals(formattedDate) && toDate.after(formattedDate)){
                isValid = true;

            }else if(toDate != null && fromDate.equals(formattedDate) && toDate.equals(formattedDate)){
                isValid = true;
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong with date insertion. Ensure that the date is formatted: dd-MM-yy");
        }
        return isValid;
    }

    @Override
    public Integer getId() {
        return this._id;
    }

    @Override
    public String getPatientNumber() {
        return patient.getPatientNumber();
    }

    public String getIname() {
        return iname;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
