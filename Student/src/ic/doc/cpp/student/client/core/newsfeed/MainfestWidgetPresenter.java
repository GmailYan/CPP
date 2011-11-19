package ic.doc.cpp.student.client.core.newsfeed;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class MainfestWidgetPresenter extends
		PresenterWidget<MainfestWidgetPresenter.MyView> {
	
	public interface MyView extends View {
		HandlerRegistration setEditLabel(String label, String title, ClickHandler handler);
		HandlerRegistration addTitleClickHandler(ClickHandler handler);
		void addPanels(PanelLabel[] panels);
		HandlerRegistration addRecordClickHandler(RecordClickHandler handler);
	}
	
	
	@Inject
	public MainfestWidgetPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	private final LoaderList toLoad = new LoaderList();
	private static Feed loadingFeed = null;
	
	@Override
	protected void onBind() {
		super.onBind();
		
		registerHandler(getView().addRecordClickHandler(new RecordClickHandler() {
			
			@Override
			public void onRecordClick(RecordClickEvent event) {
				if (event.getRecord() instanceof PanelLabel) {
					PanelLabel record = (PanelLabel)event.getRecord();
					Command cmd = record.getCmd();
					if (cmd != null) {
						cmd.execute();
					}
				}
			}
		}));
		
		registerHandler(getView().addTitleClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				refresh();
			}
		}));
		
		registerHandler(getView().setEditLabel("Edit", "Edit Category Lists", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO add handler
			}
		}));
	}

	@Override
	protected void onHide() {
		super.onHide();
	}

	@Override
	protected void onReset() {
		super.onReset();
		refresh();
	}

	public void refresh() {
		if (toLoad.size() == 0 && loadingFeed == null) {
			GWT.log("refresing mainfest widget presenter...");
			
			Map<Long, Feed> feedsMap = EventsFeedPresenter.feedMap;
			Set<Long> ids = feedsMap.keySet();
			
			for (Long id : ids) {
				GWT.log("" + id);
				Feed feed = feedsMap.get(id);
				toLoad.add(feed);
			}
		} else {
			SC.say("Still loading...Please waite...");
		}
		
	}
	
	private class LoaderList extends ArrayList<Feed> {
		private static final long serialVersionUID = 5522227181384963253L;

		ArrayList<PanelLabel> labels = new ArrayList<PanelLabel>(); 
		
		final Timer t = new Timer() {
		  public void run() {
			  if (loadingFeed == null || !loadingFeed.isLoadFinished())
				  return;
			  
			  if (size() == 0) {
				  getView().addPanels(labels.toArray(new PanelLabel[0]));
				  labels.clear();
				  loadingFeed = null;
				  return;
			  }
			  
			  Feed f = get(0);
			  
			  if (f.isLoadFinished()) {
				  GWT.log("Loading " + f.getTitle());
				  labels.add(f.getLabel());
				  remove(0);
			  } else if (!f.isLoadStarted()) {
				  loadingFeed = f;
				  f.loadFeed();
			  }
		  }
		};
		
		public LoaderList() {
		  t.scheduleRepeating(100);
		}
		
		public boolean add(Feed feed) {
		  if (contains(feed)) {
		    return false;
		  }
		  if (size() == 0) {
			  loadingFeed = feed;
			  feed.loadFeed();
		  }
		  return super.add(feed);
		}
	}
	  

}
