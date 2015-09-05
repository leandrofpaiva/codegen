package br.com.epet.velocity.test;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Properties;

/**
 * @author javadev
 */
public class MessageVelocity
{
  private VelocityEngine velocityEngine;

  public MessageVelocity()
  {
    try
    {
      Properties properties = new Properties();
      properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));

      // Cria e inicializa o engine do velocity
      velocityEngine = new VelocityEngine(properties);
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }

  public String execute()
  {
    try
    {
      MessageBean messageBean = new MessageBean();
	  messageBean.setMessage("Exemplo de uso do Velocity");

      // Build a context to hold the model
      VelocityContext velocityContext = new VelocityContext();
      velocityContext.put("msg", messageBean);

      // Execute the template
      StringWriter writer = new StringWriter();
      velocityEngine.mergeTemplate("message.vm", "utf-8", velocityContext, writer);

      // Return the result
      return writer.toString();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }

  public static void main( String[] args )
  {
    MessageVelocity message = new MessageVelocity();
    System.out.println(message.execute());
  }
}
