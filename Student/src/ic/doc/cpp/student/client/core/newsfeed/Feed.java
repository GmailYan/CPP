package ic.doc.cpp.student.client.core.newsfeed;

import ic.doc.cpp.student.shared.action.RetrieveEventsUsingCategoryId;
import ic.doc.cpp.student.shared.action.RetrieveEventsUsingCategoryIdResult;
import ic.doc.cpp.student.shared.dto.EventDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.shared.DispatchAsync;

public class Feed {
	
	private final DispatchAsync dispatcher;
	private final MainfestWidgetPresenter parent;
	private final EventsPanelWidgetPresenter current;
	
	private Date updateTime = new Date(0);
	
	private final Long feedId;
	private String title;
	private String icon;
	private List<PanelLabel> eventLabels;
	private int unReadEvents = 0;
	private PanelLabel label;
	private boolean loadStarted, loadFinished;
	
	
	public Feed(final Long feedId, String title, String icon,
			final EventBus eventBus, final DispatchAsync dispatcher,
			final MainfestWidgetPresenter parent, 
			final EventsPanelWidgetPresenter current) {
		this.feedId = feedId;
		this.title = title;
		this.icon = icon;
		this.dispatcher = dispatcher;
		this.parent = parent;
		this.current = current;
		
		loadStarted = false;
		loadFinished = false;
		
		label = new PanelLabel(icon, title);
		eventLabels = new ArrayList<PanelLabel>();
		
		final Feed thiz = this;
		
		label.setClickHandler(new Command() {
			
			@Override
			public void execute() {
				RevealNewsFeedPageEvent.fire(eventBus, current, thiz);
			}
		});
	}
	
	public void loadFeed() {
		GWT.log("Loading feed...");
		
		loadStarted = true;
		loadFinished = false;
		
	    dispatcher.execute(new RetrieveEventsUsingCategoryId(feedId, updateTime), 
	    		new AsyncCallback<RetrieveEventsUsingCategoryIdResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Fail on RetrieveEventsUsingCategoryId - " + caught.getLocalizedMessage());
						loadStarted = false;
					    loadFinished = true;
					}

					@Override
					public void onSuccess(
							RetrieveEventsUsingCategoryIdResult result) {
						List<EventDto> newCommingEvents = result.getEventDtos();
						unReadEvents += newCommingEvents.size();
						
						for (EventDto eventDto : newCommingEvents) {
							GWT.log(eventDto.getTitle());
							
							PanelLabel panelLabel = constructPanelLabel(eventDto);
							
							panelLabel.setClickHandler(new Command() {
								private boolean hasRead = false;
								@Override
								public void execute() {
									if (!hasRead) {
										--unReadEvents;
										hasRead = true;
										label.setTitle(unReadEvents);
									}
								}
							});
							eventLabels.add(0, panelLabel);
						}
						updateTime = new Date();
						label.setTitle(unReadEvents);
						loadStarted = false;
					    loadFinished = true;
					}
		});
	    
	}

	private PanelLabel constructPanelLabel(EventDto eventDto) {
		PanelLabel panelLabel = new PanelLabel(eventDto.getPicture(), eventDto.getTitle());
		panelLabel.setAttribute("eventId", eventDto.getEventId());
		panelLabel.setAttribute("description", eventDto.getDescription());
		panelLabel.setAttribute("startDate", DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM).format(eventDto.getStart_date()));
		panelLabel.setAttribute("endDate", DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM).format(eventDto.getEnd_date()));
		panelLabel.setAttribute("website", eventDto.getWebsite());
		panelLabel.setAttribute("liked", eventDto.isLikedByCurrentUser());
		return panelLabel;
	}
	
	public MainfestWidgetPresenter getParent() {
		return parent;
	}

	public EventsPanelWidgetPresenter getCurrent() {
		return current;
	}

	public int getUnReadEvents() {
		return unReadEvents;
	}

	public Long getFeedId() {
		return feedId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<PanelLabel> getEventLabels() {
		return eventLabels;
	}

	public void setEventLabels(List<PanelLabel> eventLabels) {
		this.eventLabels = eventLabels;
	}

	public void setUnReadEvents(int unReadEvents) {
		this.unReadEvents = unReadEvents;
	}

	public PanelLabel getLabel() {
		return label;
	}

	public boolean isLoadStarted() {
		return loadStarted;
	}

	public boolean isLoadFinished() {
		return loadFinished;
	}


}
