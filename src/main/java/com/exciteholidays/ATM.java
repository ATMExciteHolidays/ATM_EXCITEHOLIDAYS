package com.exciteholidays;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.exciteholidays.model.NoteATM;

public class ATM {

	private Scanner input = new Scanner(System.in);
	
	private final String XMLFILE = "notes.xml"; 
	
	private static final Logger logger = Logger.getLogger(ATM.class);
	
	private static List<NoteATM> noteATMs =  new ArrayList<NoteATM>();
	
	public static void main(String[] args) {
		ATM atm = new ATM();
		atm.initMain(args);
		atm.mainMenu();
	}

	public void initMain(String[] args) {
		System.out.println("Welcome to the initialisation of ATM EXICTEHOLIDAYS");
		initNotes(XMLFILE);
		if (noteATMs != null)
		for (NoteATM note : noteATMs) {
			System.out.println("How many " + note.getName() + " note do you want to have in this ATM ?");
			
			int numberAvailable = input.nextInt();			
			System.out.println(numberAvailable);

			note.setNumberAvailable(numberAvailable);
		}
		
		System.out.println("Thank you for the initialisation of ATM EXICTEHOLIDAYS");
	}

	
	public void initNotes(String fileName) {
		try {
			File fXmlFile = new File(fileName);
			if (fXmlFile != null && fXmlFile.canRead() ) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				if ( doc != null ) {
					doc.getDocumentElement().normalize();
					NodeList nList = doc.getElementsByTagName("note");
					if (nList != null) {
						for (int i = 0; i < nList.getLength(); i++) {
							Node nNode = nList.item(i);
							if (nNode != null &&  nNode.getNodeType() == Node.ELEMENT_NODE) {
								NoteATM note = new NoteATM();
								Element eElement = (Element) nNode;	
								if (eElement != null) {
									if ( eElement.getElementsByTagName("name") != null && eElement.getElementsByTagName("name").getLength() > 0) {
										note.setName( eElement.getElementsByTagName("name").item(0).getTextContent() );
									}
									if (eElement != null && eElement.getElementsByTagName("value") != null && eElement.getElementsByTagName("value").getLength() > 0) {
										String valueStr =  eElement.getElementsByTagName("value").item(0).getTextContent();
										note.setValue(Integer.valueOf(valueStr));
									}
									if (note.getName() != null && note.getName().trim().length() > 0 && 
										note.getValue() != null && note.getValue() >= 0) {
										noteATMs.add(note);
									} else {
										logger.error("The note can't be use, please look the file: " + XMLFILE);
									}
								}
							}
						}
					}
				}
			}
	    } catch (Exception e) {
	    	logger.error("ERROR in function-initNotes" + e.toString());
	    	logger.error("ERROR for the file : " + fileName); 
	    	
	    }
	}

	public void mainMenu() {
		boolean isNotFinsih = true;
		
		//Need to sort the list of note by value
		Collections.sort(noteATMs);
		
		while (isNotFinsih) {
			System.out.println("---------");
			System.out.println("Please enter the amount you want to get ? (-1 to display the report, -2 to exit)");
			int menuInput = input.nextInt();
			
			if (menuInput == -1) {
				getReport();
			} else if (menuInput == -2) {
				System.out.println("Thank you !");
				isNotFinsih = false;
			} else if (menuInput > 0) {
				
				//List of note with 0 note available 
				List<NoteATM> noteATMWithNoAvailalbeNote = copyNoteATMList(noteATMs);
				for (NoteATM noteATM : noteATMWithNoAvailalbeNote) {
					noteATM.setNumberAvailable(0);
				}
				//call the algorithm to get the list of all solution 
				List<List<NoteATM>> allSolutions = findAllSolution(noteATMs, noteATMWithNoAvailalbeNote, menuInput, 0);
				
				
				StringBuilder strToPrint = new StringBuilder(); 
				if (allSolutions != null && allSolutions.size() > 0 ) {
					List<NoteATM> noteATMRemoveAmounts = allSolutions.get(allSolutions.size() - 1);
					//Collections.sort(noteATMs, Collections.reverseOrder());
					for (NoteATM noteATMRemoveAmount : noteATMRemoveAmounts) {
						if ( noteATMRemoveAmount.getNumberAvailable() > 0 ) {
							for (NoteATM noteATM : noteATMs) {
								if (noteATMRemoveAmount.getName().equals(noteATM.getName())) {
									noteATM.setNumberAvailable( noteATM.getNumberAvailable() - noteATMRemoveAmount.getNumberAvailable());
									strToPrint.append("Please get " + noteATMRemoveAmount.getNumberAvailable() + " note(s) of " + noteATM.getName() + "\n");
									break;
								}
							}
						}
					}
				} else {
					strToPrint.append("Sorry, but the ATM doesn't have enough found available for your request !");
				}
				System.out.println(strToPrint.toString());
				
			} else {
				logger.error("the amount is wrong !");
				System.out.println("the amount is wrong !");
			}
		}
		System.exit(0);
	}
	
	public List<List<NoteATM>> findAllSolution(List<NoteATM> noteAvailable,  List<NoteATM> tmpNotes , int amount, int position){
        List<List<NoteATM>> list = new ArrayList<>();
        int value = 0;
        for (int i = 0; i < noteAvailable.size(); i++) {
        	value += noteAvailable.get(i).getValue() * tmpNotes.get(i).getNumberAvailable();
        }
        if (value < amount){
            for (int i = position; i < tmpNotes.size(); i++) {
                if (noteAvailable.get(i).getNumberAvailable() > tmpNotes.get(i).getNumberAvailable() ){ 
                	List<NoteATM> copyNotes = copyNoteATMList(tmpNotes);
                	if (copyNotes != null && copyNotes.size() >= i) {
                		//increase the number available by 1 and call the same function
                    	copyNotes.get(i).setNumberAvailable(  copyNotes.get(i).getNumberAvailable() + 1 );
                    	List<List<NoteATM>> newList2 = findAllSolution(noteAvailable, copyNotes, amount, i);
                        if (newList2 != null){
                            list.addAll(newList2);
                        }
                	}
                }
            }
        } else if (value == amount) {
            list.add(tmpNotes);
        }
        return list;
    }    

	public static List<NoteATM> copyNoteATMList(List<NoteATM> noteATMs) {
		List<NoteATM> copy = new ArrayList<>();
        for(NoteATM note: noteATMs){
        	copy.add((NoteATM)note.clone());
        }
        return copy;
	}

	public void getReport() {
		for (NoteATM noteAtm : noteATMs) {
			System.out.println("The ATM have " + noteAtm.getNumberAvailable() + " note(s) available for " + noteAtm.getName() );
		}
	}
}
