package com.relayd.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.relayd.attributes.Comment;

/**
 * @author  schmollc (Christian@relayD.de)
 * @since   25.08.2016
 * status   initial
 */
@FacesConverter("com.relayd.web.converter.CommentValueObjectConverter")
public class CommentValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		Comment comment = Comment.newInstance(value);
		return comment;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}