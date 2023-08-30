package com.kajol.smartHome;
import java.util.*;
class smartdevice
{
	
	String status;
	String name;
	String location;
	List <SmartInterface> device;
	
	smartdevice(SmartInterface interfacename,String name,String location)
	{
		this.name=name;
		this.location=location;
		this.status="OFF";
		this.device= new ArrayList<>();
		device.add(interfacename);
		
	}
	
	boolean getOnAndOff(String status)
	{
		if(this.status.equals(status))
			return false;
		else
			this.status=status;
		return true;
	}
	boolean ChangeColor(String command)
	{
		return false;
	}
	boolean increseBrightness(Integer brightness)
	{
		return false;
	}
	
	boolean increasespeed(Integer speed)
	{return false;}
}

class Light extends smartdevice
{
	String color;
	Integer brightness;
	String location;
	
	Light(SmartInterface interfacename,String name,String location)
	{
		super(interfacename,name,location);
		this.status="OFF";
		this.brightness=0;
		this.color=null;
		this.name=name;
		this.location=location;
	}
	
	boolean increseBrightness(Integer brightness)
	{
		if(brightness <=10 || brightness >0)
			this.brightness=brightness;
		else
			return false;
		return true;
	}
	
	boolean ChangeColor(String colore)
	{
		if(colore.equals("RED") || colore.equals("White") ||colore.equals("BLUE") ||colore.equals("GREEN"))
			this.color=colore;
		else
			return false;
		
		return true;
		
	}
	
	
}

class Fan extends smartdevice
{
	
	Integer speed;
	//String interfacename;
	
	Fan(SmartInterface interfacename,String name,String location)
	{
		super(interfacename,name,location);
		this.status="OFF";
		this.name=name;
		this.location=location;
		this.speed=0;
	}
	boolean increasespeed(Integer speed)
	{
		if(speed<=5 && speed>0)
			{
			   this.speed=speed;
			   return true;
			}
		else
			return false;
		
	}

}
class SmartInterface
{
	String name;
	String location;
	String command;
	smartdevice device;
	
	SmartInterface(String name, String locations, String command)
	{
		this.name=name;
		this.command=command;
		this.location=locations;
		this.device=null;
	}

}

class SmartHome
{
  List<SmartInterface> interfaces;
  List<smartdevice> smartdevice;
  
  SmartHome()
  {
	  this.interfaces=new ArrayList<>();
	  this.smartdevice=new ArrayList<>();
  }
  
  void addInterfaceDevice(String name, String location, String command)
  {
	 interfaces.add(new SmartInterface(name,location,command));
   }

  void smartHomeDevice(String interfacename,String name, String location)
  {
	  SmartInterface interfaceDevice=getInterfaceDevice( interfacename, location);
	  smartdevice device;
	  if(name.equals("Fan"))
		 device=new Fan(interfaceDevice,name,location);
	  else if(name.equals("Light"))
		 device=new Light(interfaceDevice,name,location);
	  else
		 device= new  smartdevice(interfaceDevice,name,location);
	 
	  if(interfaceDevice!=null)
	  {
		  interfaceDevice.device=device;
	  }
	  else
	  {
		System.out.print("device not availble");
	  }	  
  }
  void giveCommand(String interfaceName, String name, String location,String command)
  {
	  SmartInterface interfaceDevice=getInterfaceDeviceforcommand( interfaceName, location,name);
	  if(interfaceDevice!=null)
	  {
		  if(command.equals("ON") || command.equals("OFF"))
		  { boolean ans= interfaceDevice.device.getOnAndOff(command);
		    if(ans)  
		      System.out.println("OK, Drawing Room Light ");
		    else
		    	 System.out.println("already"+command);	
		  }
		  if(name.equals("LIGHT"))
		  {
			  if(command.equals("RED") || command.equals("White") ||command.equals("BLUE") ||command.equals("GREEN"))
			  {
				  interfaceDevice.device.ChangeColor(command);
			  }
			  
		  }
		  
	  }
	  
  }
  SmartInterface getInterfaceDevice(String interfacename, String location)
  {
	  for(SmartInterface interface1: interfaces)
	  {
		  if(interfacename.equals(interface1.name) && location.equals(interface1.location))
			  return interface1;
	  }
	  
	  return null;
  }
  SmartInterface getInterfaceDeviceforcommand(String interfacename, String location,String name)
  {
	  for(SmartInterface interface1: interfaces)
	  {
		  if(interfacename.equals(interface1.name) && location.equals(interface1.location) && name.equals(interface1.device.name))
			  return interface1;
	  }
	  
	  return null;
  }
}


public class SmartHomeSystem {
 
	public static void main(String[] args)
	{
		SmartHome system= new SmartHome();
		Scanner sc= new Scanner(System.in);
		boolean ans=true;
		while(ans)
		{
			System.out.println("enter command");
			String command=sc.nextLine();
			String[] commands=command.split("(");
			if(commands[0].equals("add_interface_device"))
			{
				String[] command1=commands[1].split(",");
				system.addInterfaceDevice(command1[0], command1[1], command1[2]);
			}
			else if(commands[0].equals("add_smarthome_device"))
			{
				String[] command1=commands[1].split(",");
				system.smartHomeDevice(command1[0], command1[1], command1[2]);
			}
			else if(commands[0].equals("ive_command"))
			{
				String[] command1=commands[1].split(",");
				system.giveCommand(command1[0], command1[1], command1[2], command1[3]);
			}
			
		}
	}
}
