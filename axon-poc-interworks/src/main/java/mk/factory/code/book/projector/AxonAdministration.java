package mk.factory.code.book.projector;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AxonAdministration {
	@Autowired
	private EventProcessingConfiguration eventProcessingConfiguration;

	public void resetTrackingEventProcessor(String processingGroup) {
		this.eventProcessingConfiguration.eventProcessorByProcessingGroup(processingGroup, TrackingEventProcessor.class)
				.ifPresent(trackingEventProcessor -> {
					trackingEventProcessor.shutDown();
					trackingEventProcessor.resetTokens();
					trackingEventProcessor.start();

				});
	}
	
	public void resetTrackingEventProcessorByDays(String processingGroup, long days) {
		Instant nowDate = Instant.now();
		Instant result = nowDate.minus(days, ChronoUnit.MINUTES);

		this.eventProcessingConfiguration.eventProcessorByProcessingGroup(processingGroup, TrackingEventProcessor.class)
				.ifPresent(trackingEventProcessor -> {
					trackingEventProcessor.shutDown();
					trackingEventProcessor.resetTokens(
							(streamableMessageSource -> 
							streamableMessageSource.createTokenAt(result))
							);
					trackingEventProcessor.start();

				});
	}
}
