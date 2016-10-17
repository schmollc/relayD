package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.relayd.Relay;
import com.relayd.attributes.Relayname;
import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeImpl;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
@ManagedBean(name = "relayEditPageBean")
@SessionScoped
public class RelayEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Relay workingRelay;

	private RelayBridge relayBridge;

	public RelayEditPageBean() {
		relayBridge = new RelayBridgeImpl();
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingRelay);
	}

	public void cancel() {
		closeDialog();
	}

	public void setRelayname(Relayname aRelayname) {
		workingRelay.setRelayname(aRelayname);
	}

	public Relayname getRelayname() {
		return workingRelay.getRelayname();
	}

	public void openDialogForCreateRelay() {
		prepareNewRelay();
		openDialog();
	}

	void prepareNewRelay() {
		workingRelay = createNewRelay();
	}

	Relay createNewRelay() {
		Relay relay = Relay.newInstance();
		return relay;
	}

	public void save() {
		persistRelay();
		closeDialog();
	}

	void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().height(100).build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.RELAY_EDIT_DIALOG_ID, options, null);
	}

	void persistRelay() {
		getBridge().create(workingRelay);
	}

	private RelayBridge getBridge() {
		return relayBridge;
	}
}