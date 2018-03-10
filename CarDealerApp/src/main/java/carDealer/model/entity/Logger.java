package carDealer.model.entity;

import carDealer.model.enums.Operation;
import carDealer.model.enums.TableEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by George-Lenovo on 03/08/2018.
 */
@Entity
@Table(name = "logs")
public class Logger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Operation operation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TableEnum modifiedTable;

    @Column(nullable = false)
    private Date date;


    public Logger() {
    }

    public Logger(User user, Operation operation, TableEnum modifiedTable, Date date) {
        this.user = user;
        this.operation = operation;
        this.modifiedTable = modifiedTable;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TableEnum getModifiedTable() {
        return modifiedTable;
    }

    public void setModifiedTable(TableEnum modifiedTable) {
        this.modifiedTable = modifiedTable;
    }

}


