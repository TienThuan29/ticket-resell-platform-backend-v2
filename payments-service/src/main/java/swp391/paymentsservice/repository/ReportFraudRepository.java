package swp391.paymentsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.GenericTicket;
import swp391.entity.ReportFraud;
import swp391.entity.fixed.GeneralProcess;

import java.util.List;

@Repository
public interface ReportFraudRepository extends JpaRepository<ReportFraud, Long> {

    @Query("SELECT r FROM GenericTicket gt INNER JOIN Ticket t " +
            "ON gt.id=t.genericTicket.id INNER JOIN ReportFraud r " +
            "ON r.ticket.id = t.id " +
            "WHERE gt in :genericTickets AND r.process = :process")
    List<ReportFraud> getReportByGenericTickets(List<GenericTicket> genericTickets, GeneralProcess process);

//    @Query("SELECT r FROM GenericTicket gt INNER JOIN Ticket t " +
//            "ON gt.id=t.genericTicket.id INNER JOIN ReportFraud r " +
//            "ON r.ticket.id = t.id " +
//            "WHERE gt in :genericTickets")
//    List<ReportFraud> getReportByGenericTicketsT1(List<GenericTicket> genericTickets);
}
