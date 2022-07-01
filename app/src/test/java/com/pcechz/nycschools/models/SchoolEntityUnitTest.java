package com.pcechz.nycschools.models;

import com.pcechz.nycschools.data.db.entity.SchoolEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SchoolEntityUnitTest {
    @Test
    public void testId(){
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setId(1000);
        assertEquals(schoolEntity.getId(), 1000);
    }

    @Test
    public void testDbn(){
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setDbn("21K728");
        assertEquals(schoolEntity.getDbn(), "21K728");
    }

    @Test
    public void testSchoolName(){
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setSchoolName("Clinton School Writers & Artists, M.S. 260");
        assertEquals(schoolEntity.getSchoolName(), "Clinton School Writers & Artists, M.S. 260");
    }

    @Test
    public void testPhoneNumber(){
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setPhoneNumber("212-524-4360");
        assertEquals(schoolEntity.getPhoneNumber(), "212-524-4360");
    }

    @Test
    public void testSchoolEmail(){
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setSchoolEmail("admissions@theclintonschool.net");
        assertEquals(schoolEntity.getSchoolEmail(), "admissions@theclintonschool.net");
    }

    @Test
    public void testWebsite(){
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setWebsite("www.theclintonschool.net");
        assertEquals(schoolEntity.getWebsite(), "www.theclintonschool.net");
    }

    @Test
    public void testCity(){
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setCity("Manhattan");
        assertEquals(schoolEntity.getCity(), "Manhattan");
    }

    @Test
    public void testStateCode(){
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setStateCode("NY");
        assertEquals(schoolEntity.getStateCode(), "NY");
    }

    @Test
    public void testOverviewParagraph(){
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setOverviewParagraph("Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities.");
        assertEquals(schoolEntity.getOverviewParagraph(), "Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities.");
    }


}
