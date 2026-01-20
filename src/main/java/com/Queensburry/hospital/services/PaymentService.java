package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.PaymentRequestDto;
import com.Queensburry.hospital.entity.DoctorAppointment;
import com.Queensburry.hospital.entity.Patient;
import com.Queensburry.hospital.entity.Payment;
import com.Queensburry.hospital.repo.DoctorAppointmentRepo;
import com.Queensburry.hospital.repo.PatientRepo;
import com.Queensburry.hospital.repo.PaymentRepo;
import com.Queensburry.hospital.utils.EmailSender;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorAppointmentRepo doctorAppointmentRepo;

    @Autowired
    private EmailSender emailSender;



    public String savePayment(PaymentRequestDto paymentRequestDto) {
        try {

            Patient patient = patientRepo.findById(paymentRequestDto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));


            DoctorAppointment doctorAppointment = doctorAppointmentRepo
                    .findById(paymentRequestDto.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Doctor appointment not found"));


            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            Date paymentDate = Date.valueOf(localDate);
            Time paymentTime = Time.valueOf(localTime);


            Payment payment = new Payment(
                    null,
                    patient,
                    doctorAppointment,
                    paymentRequestDto.getAmount(),
                    paymentRequestDto.getPaymentMethod(),
                    paymentDate,
                    paymentTime
            );

            Payment saved = paymentRepo.save(payment);
            if (saved.getPayment_id() == null) {
                return "Payment could not be saved";
            }


            Optional<DoctorAppointment> appointmentOpt =
                    doctorAppointmentRepo.findById(paymentRequestDto.getAppointmentId());

            if (appointmentOpt.isPresent()) {
                DoctorAppointment appointment = appointmentOpt.get();
                appointment.setPayment_status(true);
                doctorAppointmentRepo.save(appointment);
            } else {
                throw new RuntimeException("Doctor appointment not found");
            }



            String firstName = patient.getUser() != null ?
                    Optional.ofNullable(patient.getUser().getFirstName()).orElse("") : "";
            String lastName = patient.getUser() != null ?
                    Optional.ofNullable(patient.getUser().getLastName()).orElse("") : "";
            String email = patient.getUser() != null ?
                    Optional.ofNullable(patient.getUser().getEmail()).orElse("") : "";

            Map<String, Object> variables = new HashMap<>();
            variables.put("patientName", (firstName + " " + lastName).trim());
            variables.put("patientId", patient.getPatientId() != null ? patient.getPatientId() : "");
            variables.put("appointmentId", doctorAppointment.getAppointmentId() != null ? doctorAppointment.getAppointmentId() : "");
            variables.put("amount", paymentRequestDto.getAmount() != null ? paymentRequestDto.getAmount() : 0);
            variables.put("paymentMethod", paymentRequestDto.getPaymentMethod() != null ? paymentRequestDto.getPaymentMethod() : "");
            variables.put("paymentDate", localDate.toString());
            variables.put("paymentTime", localTime.toString());
            variables.put("clinicName", "Queensburry Hospital");
            variables.put("clinicUrl", "http://localhost:8090/patient/appointments");
            variables.put("clinicDomain", "queensburryhospital.com"); // needed in email sender
            variables.put("year", String.valueOf(localDate.getYear()));






            if (!email.isEmpty()) {
                try {
                    emailSender.sendPaymentSuccessEmail(email, variables);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return saved.getPayment_id() + " saved, but failed to send email: " + e.getMessage();
                }
            }

            return saved.getPayment_id() + " saved successfully. Payment email sent.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }


}
