package rs.odgajivacnica.admin.commons;


public interface MessageAwareView {
  public enum MessageType {
    INFO("KTP_MSG_I"), WARNING("KTP_MSG_W"), ERROR("KTP_MSG_E");
    

    private String label;
    
    private MessageType(String label) {
      this.label = label;
    }

    public String getLabel() {
      return label;
    }
  }
  
  public MessageAwareView addInfo(String i18nKey);
  
  public MessageAwareView addWarning(String i18nKey);
  
  public MessageAwareView addError(String i18nKey);
}
