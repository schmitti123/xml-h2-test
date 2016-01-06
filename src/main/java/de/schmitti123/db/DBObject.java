package de.schmitti123.db;

import javax.persistence.*;
import java.util.Date;

/**
 * Class in package ${PACKAGE_NAME} from project xml-h2-timezonetest
 * Created by mschmitt on 06.01.16.
 */
@Entity
@Table(name = "DB_OBJECT")
@NamedQueries({
        @NamedQuery(name = DBObject.NAMED_QUERY_MAX_TIME, query = "select max(p.time) from DBObject p where p.time < CURRENT_TIMESTAMP "),
        @NamedQuery(name = DBObject.NAMED_QUERY_ACUTE_TIME, query = "select p from DBObject p where p.time = :lala")


})
public class DBObject {

    public static final String NAMED_QUERY_MAX_TIME = "DBObject.maxTime";
    public static final String NAMED_QUERY_ACUTE_TIME = "DBObject.acuteTime";

    private Long id;
    private String desc;
    private Date time;

    public DBObject() {

    }

    public DBObject(String desc, Date time) {
        this.setDesc(desc);
        this.setTime(time);

    }
    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DESC")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Column(name = "TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id);
        sb.append("; ");
        sb.append("desc: ").append(desc);
        sb.append("; ");
        sb.append("time: ").append(time);

        return sb.toString();

    }
}
