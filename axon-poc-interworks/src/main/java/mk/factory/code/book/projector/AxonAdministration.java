package mk.factory.code.book.projector;

import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AxonAdministration {
	//@Autowired
	private EventProcessingConfiguration eventProcessingConfiguration;
	
	public AxonAdministration(EventProcessingConfiguration eventProcessingConfiguration) {
		this.eventProcessingConfiguration = eventProcessingConfiguration;
	}
	
	 public void resetTrackingEventProcessor(String processingGroup) {
		 this.eventProcessingConfiguration
		 .eventProcessorByProcessingGroup(processingGroup, TrackingEventProcessor.class)
	       .ifPresent(trackingEventProcessor -> {
               trackingEventProcessor.shutDown();
               trackingEventProcessor.resetTokens();
               trackingEventProcessor.start();
           });
	 }
}
