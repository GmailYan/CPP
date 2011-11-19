package ic.doc.cpp.student.client.core.newsfeed;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.gwtplatform.mvp.client.PresenterWidget;

public class RevealNewsFeedPageEvent extends
		GwtEvent<RevealNewsFeedPageEvent.RevealNewsFeedPageHandler> {

	private final PresenterWidget<?> presenter;
	private Feed feed;
	
	public static Type<RevealNewsFeedPageHandler> TYPE = new Type<RevealNewsFeedPageHandler>();

	public interface RevealNewsFeedPageHandler extends EventHandler {
		void onRevealNewsFeedPage(RevealNewsFeedPageEvent event);
	}

	public RevealNewsFeedPageEvent(final PresenterWidget<?> presenter, Feed feed) {
		this.presenter = presenter;
		this.feed = feed;
	}

	@Override
	protected void dispatch(RevealNewsFeedPageHandler handler) {
		handler.onRevealNewsFeedPage(this);
	}

	@Override
	public Type<RevealNewsFeedPageHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<RevealNewsFeedPageHandler> getType() {
		return TYPE;
	}

	public static void fire(EventBus eventBus, PresenterWidget<?> presenter, Feed feed) {
		eventBus.fireEvent(new RevealNewsFeedPageEvent(presenter, feed));
	}

	public PresenterWidget<?> getPresenter() {
		return presenter;
	}
	
	public Feed getFeed() {
		return feed;
	}
}
