package de.schmitti123.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mschmitt on 06.01.16.
 */
@XmlRootElement
public class XMLObject {


    private List<Times> times = new ArrayList<Times>();

    @XmlElementWrapper(name="times")
    @XmlElement(name = "time")
    public List<Times> getTimes() {
        return times;
    }

    public void setTimes(List<Times> times) {
        this.times = times;
    }

    public static class Times  {
        private Date time;
        private String desc;


        @XmlElement
        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        @XmlElement()
        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }
}
