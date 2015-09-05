package br.com.epet.velocity.test;

/**
 * @author javadev
 */
public class MessageBean
{
  private String message;

  public MessageBean()
  {
  }

  public MessageBean(String message)
  {
    this.message = message;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }
}
