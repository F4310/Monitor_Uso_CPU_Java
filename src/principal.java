import java.io.ObjectInputStream.GetField;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class principal {
	
	static Double value;
	 static Double media = 00.00 ;

	public static void printUsage(int sec) throws InterruptedException {
		DecimalFormat df = new DecimalFormat("##.##");
		
		int time = sec;
		sec = 60*sec;
		sec = sec*2;
		int i= 1;
		
	    while (i<=sec){
	  OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
	  for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
	    method.setAccessible(true);
	   
	   if(method.getName().startsWith("getSystemCpuLoad")){

	        try {
	        	
	            value = (Double) method.invoke(operatingSystemMXBean);
	            value = value * 100;
	           
	        } 
	        
	        catch (Exception e) {
	            value = 1.007;
	        } // try
	        
	        System.out.println(method.getName() + " = " + value);
	        
	        media = media + value;
	        Thread.sleep(500);
	        i=i+1;
	        
	    } // if
	  } // for
	}
	    i = i -1;	
	    media = media/i;
	    JOptionPane.showMessageDialog(null,"Media de uso em "+time+" minuto(s) foi: "+(df.format(media))+"%" +
	    		"\nDesenvolvido por Fabio Ubiratan");
	}
	
	public static void main (String args[]) throws InterruptedException{
		
		int sec;
		
		sec = Integer.parseInt(JOptionPane.showInputDialog("Insira o tempo (em minuto(s)) para monitoramento:"));
		printUsage(sec);
	}
}



