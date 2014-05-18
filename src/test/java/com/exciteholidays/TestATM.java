package com.exciteholidays;

import java.util.ArrayList;
import java.util.List;

import com.exciteholidays.model.NoteATM;

import junit.framework.TestCase;
import junit.textui.TestRunner;

public class TestATM extends TestCase {
	
	 public void main(String[] args) {
		TestRunner.run(TestATM.class);
	 }
	 

	 public static NoteATM n20;
	 public static NoteATM n50;
	 
	 public TestATM(String name) {
		 super(name);
		 
		 n20 = new NoteATM();
		 n20.setName("$20");
		 n20.setValue(20);
		 
		 n50 = new NoteATM();
		 n50.setName("$50");
		 n50.setValue(50);
		
	 }
	 
	 
	 public void testFindNothing () {
		 ATM atm = new ATM();
		 
		 n20.setNumberAvailable(0);
		 n50.setNumberAvailable(1);
		 
		 List<NoteATM> noteATMs =  new ArrayList<NoteATM>();
		 noteATMs.add(n20);
		 noteATMs.add(n50);
		 
		 List<NoteATM> noteATMWithNoAvailalbeNote = new ArrayList<>();
		 for (NoteATM noteATM : noteATMs) {
			 NoteATM tmp = (NoteATM)noteATM.clone();
			 tmp.setNumberAvailable(0);
			 noteATMWithNoAvailalbeNote.add(tmp);
		 }
		 
		 List<List<NoteATM>> allSolutions = atm.findAllSolution( noteATMs, noteATMWithNoAvailalbeNote, 20, 0);
		 
		 assertTrue(allSolutions.size() == 0);
		 
	 }
	 
	 
	 public void testFindNote20 () {
		 ATM atm = new ATM();
		 
		 n20.setNumberAvailable(1);
		 n50.setNumberAvailable(1);
		 
		 List<NoteATM> noteATMs =  new ArrayList<NoteATM>();
		 noteATMs.add(n20);
		 noteATMs.add(n50);
		 
		 List<NoteATM> noteATMWithNoAvailalbeNote = new ArrayList<>();
		 for (NoteATM noteATM : noteATMs) {
			 NoteATM tmp = (NoteATM)noteATM.clone();
			 tmp.setNumberAvailable(0);
			 noteATMWithNoAvailalbeNote.add(tmp);
		 }
		 
		 List<List<NoteATM>> allSolutions = atm.findAllSolution( noteATMs, noteATMWithNoAvailalbeNote, 20, 0);
		 
		 assertTrue(allSolutions.size() == 1);
		 List<NoteATM> result = allSolutions.get(0);
		 
		 assertTrue(result.size() == noteATMs.size());
		 
		 for (NoteATM noteATM : result) {
			if (noteATM.getName().equals(n20.getName())) {
				assertEquals(noteATM.getNumberAvailable(), 1);
			}
			if (noteATM.getName().equals(n50.getName())) {
				assertEquals(noteATM.getNumberAvailable(), 0);
			}
		 }
	 }
	 
	 public void testFindNote50 () {
		 ATM atm = new ATM();
		 
		 n20.setNumberAvailable(1);
		 n50.setNumberAvailable(1);
		 
		 List<NoteATM> noteATMs =  new ArrayList<NoteATM>();
		 noteATMs.add(n20);
		 noteATMs.add(n50);
		 
		 List<NoteATM> noteATMWithNoAvailalbeNote = new ArrayList<>();
		 for (NoteATM noteATM : noteATMs) {
			 NoteATM tmp = (NoteATM)noteATM.clone();
			 tmp.setNumberAvailable(0);
			 noteATMWithNoAvailalbeNote.add(tmp);
		 }
		 
		 List<List<NoteATM>> allSolutions = atm.findAllSolution( noteATMs, noteATMWithNoAvailalbeNote, 50, 0);
		 
		 assertTrue(allSolutions.size() == 1);
		 List<NoteATM> result = allSolutions.get(0);
		 
		 assertTrue(result.size() == noteATMs.size());
		 
		 for (NoteATM noteATM : result) {
			if (noteATM.getName().equals(n20.getName())) {
				assertEquals(noteATM.getNumberAvailable(), 0);
			}
			if (noteATM.getName().equals(n50.getName())) {
				assertEquals(noteATM.getNumberAvailable(), 1);
			}
		 }
	 }
	 
	 public void testFindNote70 () {
		 ATM atm = new ATM();
		 
		 n20.setNumberAvailable(1);
		 n50.setNumberAvailable(1);
		 
		 List<NoteATM> noteATMs =  new ArrayList<NoteATM>();
		 noteATMs.add(n20);
		 noteATMs.add(n50);
		 
		 List<NoteATM> noteATMWithNoAvailalbeNote = new ArrayList<>();
		 for (NoteATM noteATM : noteATMs) {
			 NoteATM tmp = (NoteATM)noteATM.clone();
			 tmp.setNumberAvailable(0);
			 noteATMWithNoAvailalbeNote.add(tmp);
		 }
		 
		 List<List<NoteATM>> allSolutions = atm.findAllSolution( noteATMs, noteATMWithNoAvailalbeNote, 70, 0);
		 
		 assertTrue(allSolutions.size() == 1);
		 List<NoteATM> result = allSolutions.get(0);
		 
		 assertTrue(result.size() == noteATMs.size());
		 
		 for (NoteATM noteATM : result) {
			if (noteATM.getName().equals(n20.getName())) {
				assertEquals(noteATM.getNumberAvailable(), 1);
			}
			if (noteATM.getName().equals(n50.getName())) {
				assertEquals(noteATM.getNumberAvailable(), 1);
			}
		 }
	 }
	 
	 public void testFindNotesFor100 () {
		 ATM atm = new ATM();
		 
		 n20.setNumberAvailable(5);
		 n50.setNumberAvailable(2);
		 
		 List<NoteATM> noteATMs =  new ArrayList<NoteATM>();
		 noteATMs.add(n20);
		 noteATMs.add(n50);
		 
		 List<NoteATM> noteATMWithNoAvailalbeNote = new ArrayList<>();
		 for (NoteATM noteATM : noteATMs) {
			 NoteATM tmp = (NoteATM)noteATM.clone();
			 tmp.setNumberAvailable(0);
			 noteATMWithNoAvailalbeNote.add(tmp);
		 }
		 
		 List<List<NoteATM>> allSolutions = atm.findAllSolution( noteATMs, noteATMWithNoAvailalbeNote, 100, 0);
		 
		 assertTrue(allSolutions.size() == 2);
		 
		 List<NoteATM> result = allSolutions.get(0);
		 assertTrue(result.size() == noteATMs.size());
		 
		 for (NoteATM noteATM : result) {
			if (noteATM.getName().equals(n20.getName())) {
				assertEquals(noteATM.getNumberAvailable(), 5);
			}
			if (noteATM.getName().equals(n50.getName())) {
				assertEquals(noteATM.getNumberAvailable(), 0);
			}
		 }
		 
		 result = allSolutions.get(1);
		 assertTrue(result.size() == noteATMs.size());
		 
		 for (NoteATM noteATM : result) {
			if (noteATM.getName().equals(n20.getName())) {
				assertEquals(noteATM.getNumberAvailable(), 0);
			}
			if (noteATM.getName().equals(n50.getName())) {
				assertEquals(noteATM.getNumberAvailable(), 2);
			}
		 }
	 }
	 
	 
	 public void testFindNotesFor200 () {
		 ATM atm = new ATM();
		 
		 n20.setNumberAvailable(20);
		 n50.setNumberAvailable(4);
		 
		 List<NoteATM> noteATMs =  new ArrayList<NoteATM>();
		 noteATMs.add(n20);
		 noteATMs.add(n50);
		 
		 List<NoteATM> noteATMWithNoAvailalbeNote = new ArrayList<>();
		 for (NoteATM noteATM : noteATMs) {
			 NoteATM tmp = (NoteATM)noteATM.clone();
			 tmp.setNumberAvailable(0);
			 noteATMWithNoAvailalbeNote.add(tmp);
		 }
		 
		 List<List<NoteATM>> allSolutions = atm.findAllSolution( noteATMs, noteATMWithNoAvailalbeNote, 200, 0);
		 
		 assertTrue(allSolutions.size() == 3);
		 
	 }
	 
	 public void testCopyList() {
		 
		 ATM atm = new ATM();
		 List<NoteATM> noteATMs =  new ArrayList<NoteATM>();
		 
		 NoteATM n1 = new NoteATM();
		 n1.setName("$20");
		 n1.setValue(20);
		 n1.setNumberAvailable(10);
		 noteATMs.add(n1);
		
		 NoteATM n2 = new NoteATM();
		 n2.setName("$50");
		 n2.setValue(50);
		 n2.setNumberAvailable(10);
		 noteATMs.add(n2);
			
		 List<NoteATM> copy = atm.copyNoteATMList(noteATMs);
		
		 assertTrue( copy.size() == noteATMs.size()  );
		 for (int i = 0; i < copy.size(); i++) {
			 assertEquals(copy.get(i).getName(), noteATMs.get(i).getName());
			 assertEquals(copy.get(i).getNumberAvailable(), noteATMs.get(i).getNumberAvailable());
			 assertEquals(copy.get(i).getValue(), noteATMs.get(i).getValue());
		 }
		 
	 }
	 
}
