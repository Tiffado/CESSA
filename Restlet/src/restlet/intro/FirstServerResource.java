package restlet.intro;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


public class FirstServerResource extends ServerResource {
	// mod for mode

	   public static void main(String[] args) throws Exception {  
		      // Create the HTTP server and listen on port 8182  
		      new Server(Protocol.HTTP, 8182, FirstServerResource.class).start();  
		   
	   //*
		   // Outputting the content of a Web page  
		      new ClientResource("http://localhost:8182").get().write(System.out);  
	   //*/
	   
		   /*
		      // Create the client resource  
		      ClientResource resource = new ClientResource("http://www.restlet.org");  
		       
		      // Customize the referrer property  
		      resource.setReferrerRef("http://www.mysite.org");  

		      // Write the response entity on the console
		      resource.get().write(System.out);  
	   //*/
	   
	   
	   }

	   @Get  
	   public String toString() {  
	      return "hello, world";
	   }

		
	
}
