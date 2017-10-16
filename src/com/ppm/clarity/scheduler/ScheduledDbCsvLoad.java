package com.ppm.clarity.scheduler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.ppm.clarity.dao.EmployeesDAO;
import com.ppm.clarity.model.Employees;

public class ScheduledDbCsvLoad {
	   private static final ScheduledExecutorService scheduler = Executors
		        .newScheduledThreadPool(1);
	   public void startScheduleTask() {
	        final Runnable job = new Runnable() {
	            public void run() {
	            	 try {
	            			System.out.println("I am here in services !!!");
		                    getDataFromDatabase();

		                }catch(Exception ex) {
		                    ex.printStackTrace(); //or loggger would be better
		                }
	            }
	        };
	        
	        final ScheduledFuture<?> jobHandle = scheduler.scheduleAtFixedRate(
  		job, 0, 2, TimeUnit.MINUTES);	

	    
	   }

	   
		    private void getDataFromDatabase() {
		        System.out.println("getting data...");
		        EmployeesDAO empDao=new EmployeesDAO();
		      loadCsv(  empDao.getEmployees());
		    }

		    private void loadCsv(List<Employees> employees) {
		    	String COMMA_DELIMITER = ",";
		    	 String NEW_LINE_SEPARATOR = "\n";
		    	 FileWriter fileWriter = null;
		    	 String userHomeFolder = System.getProperty("user.home");
		    	 String timeStamp = new SimpleDateFormat("yyMMddhhmm").format(new Date());

		    	 File csvFile = new File(userHomeFolder+"/Desktop", "emp_"+timeStamp+".csv");

		    	  try {
					fileWriter = new FileWriter(csvFile);
					for(Employees emp:employees){
						fileWriter.append(String.valueOf(emp.getEmployee_id()));
						 fileWriter.append(COMMA_DELIMITER);
						 fileWriter.append(String.valueOf(emp.getFirst_name()));
						 fileWriter.append(COMMA_DELIMITER);
						 fileWriter.append(String.valueOf(emp.getDepartment_id()));
						 fileWriter.append(COMMA_DELIMITER);
						 fileWriter.append(String.valueOf(emp.getEmail()));
						 fileWriter.append(COMMA_DELIMITER);
						 fileWriter.append(String.valueOf(emp.getHire_date()));
						 fileWriter.append(COMMA_DELIMITER);
						 fileWriter.append(String.valueOf(emp.getJob_id()));
						 fileWriter.append(COMMA_DELIMITER);
						 fileWriter.append(String.valueOf(emp.getCommission_pct()));
						 fileWriter.append(COMMA_DELIMITER);
						 fileWriter.append(String.valueOf(emp.getLast_name()));
						 fileWriter.append(COMMA_DELIMITER);
						 fileWriter.append(String.valueOf(emp.getManager_id()));
						 fileWriter.append(COMMA_DELIMITER);
						 fileWriter.append(String.valueOf(emp.getSalary()));
						 fileWriter.append(NEW_LINE_SEPARATOR);

						 
					}
					System.out.println("CSV file was created successfully !!!");

					
				} catch (IOException e) {
					System.out.println("Error in CsvFileWriter !!!");
					e.printStackTrace();
				}finally {
					

					            try {

					                fileWriter.flush();

					                fileWriter.close();
					
					            } catch (IOException e) {
					
					                System.out.println("Error while flushing/closing fileWriter !!!");
					
					                e.printStackTrace();
					
					            }
				
					        }


				
			}



			public void killScheduler() {
				System.out.println("I am here in stop fag !!!");
				
				 scheduler.shutdown();
			}


}
