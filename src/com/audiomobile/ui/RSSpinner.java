package com.audiomobile.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;


public class RSSpinner extends Spinner {
		  public RSSpinner(Context paramContext)
		  {
		    super(paramContext);
		  }

		  public RSSpinner(Context paramContext, AttributeSet paramAttributeSet)
		  {
		    super(paramContext, paramAttributeSet);
		  }

		  public RSSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
		  {
		    super(paramContext, paramAttributeSet, paramInt);
		  }

		  public void setSelection(int paramInt)
		  {
		    if (paramInt == getSelectedItemPosition());
		    for (int i = 1; ; i = 0)
		    {
		      super.setSelection(paramInt);
		      if (i != 0)
		        getOnItemSelectedListener().onItemSelected(this, getSelectedView(), paramInt, getSelectedItemId());
		      return;
		    }
		  }

		  public void setSelection(int paramInt, boolean paramBoolean)
		  {
		    if (paramInt == getSelectedItemPosition());
		    for (int i = 1; ; i = 0)
		    {
		      super.setSelection(paramInt, paramBoolean);
		      if (i != 0)
		        getOnItemSelectedListener().onItemSelected(this, getSelectedView(), paramInt, getSelectedItemId());
		      return;
		    }
		  }
}
