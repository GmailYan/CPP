package ic.doc.cpp.student.client.core.newsfeed;

import java.util.HashMap;
import java.util.Map;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import ic.doc.cpp.student.client.place.NameTokens;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import ic.doc.cpp.student.client.LoggedInGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import ic.doc.cpp.student.client.core.StudentPagePresenter;

public class EventsFeedPresenter extends
		Presenter<EventsFeedPresenter.MyView, EventsFeedPresenter.MyProxy> {

	public interface MyView extends View {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.eventfeed)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<EventsFeedPresenter> {
	}

	public static final Object TYPE_RevealEventFeedWidget = new Object();
	
	private final MainfestWidgetPresenter categoryPresenter;
	private final EventsPanelWidgetPresenter eventsPresenter;
	private final EventBus eventBus;
	private final DispatchAsync dispatcher;
	
	public static final Map<Long, Feed> feedMap = new HashMap<Long, Feed>();
	public static PresenterWidget<?> currentPresenter;
	
	@Inject
	public EventsFeedPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy,  final MainfestWidgetPresenter categoryPresenter,
			final EventsPanelWidgetPresenter events,
			final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
		this.categoryPresenter = categoryPresenter;
		this.eventsPresenter = events;
		currentPresenter = categoryPresenter;
		
		eventBus.addHandler(RevealNewsFeedPageEvent.getType(), new RevealNewsFeedPageEvent.RevealNewsFeedPageHandler() {
			
			@Override
			public void onRevealNewsFeedPage(RevealNewsFeedPageEvent event) {
				if (event.getFeed() == null) {
					currentPresenter = categoryPresenter;
				} else {
					currentPresenter = eventsPresenter;
					eventsPresenter.show(event.getFeed());
				}
				setInSlot(TYPE_RevealEventFeedWidget, event.getPresenter());
					
			}
		});
		
	}

	public void addFeed(long id, String title, String icon) {
		Feed feed = new Feed(id, title, icon, eventBus, dispatcher, categoryPresenter, eventsPresenter);
		feedMap.put(id, feed);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				StudentPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addFeed(1L, "Job", "Job.jpg");
		addFeed(4L, "Presentation", "Presentation.jpg");
	}

	@Override
	protected void onReset() {
		super.onReset();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		setInSlot(TYPE_RevealEventFeedWidget, currentPresenter);
	}
	
}
