package rs.odgajivacnica.admin.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public class MessageAwareModelAndView extends ModelAndView implements MessageAwareView {
  
  public MessageAwareModelAndView(String viewName) {
    super(viewName);
  }

  private MessageAwareModelAndView add(MessageType messageType, String i18nKey) {
    String messageTypeLabel = messageType.getLabel();
    Map<String, Object> model = this.getModel();
    
    if(model.containsKey(messageTypeLabel)) {
      List<String> messages = (List<String>) model.get(messageTypeLabel);
      messages.add(i18nKey);
    } else {
      List<String> messages = new ArrayList<String>(1);
      messages.add(i18nKey);
      
      this.addObject(messageTypeLabel, messages);
    }
    
    return this;
  }
  
  public MessageAwareModelAndView addInfo(String i18nKey) {
    return this.add(MessageType.INFO, i18nKey);
  }
  
  public MessageAwareModelAndView addWarning(String i18nKey) {
    return this.add(MessageType.WARNING, i18nKey);
  }
  
  public MessageAwareModelAndView addError(String i18nKey) {
    return this.add(MessageType.ERROR, i18nKey);
  }

}
