package rs.odgajivacnica.admin.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

public class MessageAwareRedirectView extends RedirectView implements MessageAwareView {
  
  private RedirectAttributes redirectAttributes;
  
  public MessageAwareRedirectView(String url, RedirectAttributes redirectAttributes) {
    super(url);
    super.setContextRelative(true);
    this.redirectAttributes = redirectAttributes;
  }
  
  private MessageAwareRedirectView add(MessageType messageType, String i18nKey) {
    String messageTypeLabel = messageType.getLabel();
    Map<String, Object> model = (Map<String, Object>) this.redirectAttributes.getFlashAttributes();
    
    if(model.containsKey(messageTypeLabel)) {
      List<String> messages = (List<String>) model.get(messageTypeLabel);
      messages.add(i18nKey);
    } else {
      List<String> messages = new ArrayList<String>(1);
      messages.add(i18nKey);
      
      model.put(messageTypeLabel, messages);
    }
    
    return this;
  }
  
  public MessageAwareRedirectView addInfo(String i18nKey) {
    return this.add(MessageType.INFO, i18nKey);
  }
  
  public MessageAwareRedirectView addWarning(String i18nKey) {
    return this.add(MessageType.WARNING, i18nKey);
  }
  
  public MessageAwareRedirectView addError(String i18nKey) {
    return this.add(MessageType.ERROR, i18nKey);
  }
  
}
