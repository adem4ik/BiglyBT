/*
 * File    : TypeItem.java
 * Created : 24 nov. 2003
 * By      : Olivier
 *
 * Copyright (C) Azureus Software, Inc, All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details ( see the LICENSE file ).
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.biglybt.ui.swt.views.tableitems.tracker;

import com.biglybt.pif.ui.tables.*;
import com.biglybt.ui.swt.views.table.CoreTableColumnSWT;

import com.biglybt.core.tracker.TrackerPeerSource;
import com.biglybt.core.util.DisplayFormatters;


public class
ReportedUpItem
	extends CoreTableColumnSWT
    implements TableCellRefreshListener
{
	public
	ReportedUpItem(String tableID)
	{
		super( "reported_up", ALIGN_TRAIL, POSITION_INVISIBLE, 75, tableID );

		setRefreshInterval( INTERVAL_GRAPHIC );
	}

	@Override
	public void
	fillTableColumnInfo(
		TableColumnInfo info )
	{
		info.addCategories( new String[]{
			CAT_CONTENT,
		});
		info.setProficiency(TableColumnInfo.PROFICIENCY_BEGINNER);
	}

	@Override
	public void
	refresh(
		TableCell cell )
	{
		TrackerPeerSource ps = (TrackerPeerSource)cell.getDataSource();

		long[] stats = (ps==null)?null:ps.getReportedStats();

		long value1 = stats==null?-1:stats[0];
		long value2 = stats==null||stats.length<4?-1:stats[2];
		
		if (!cell.setSortValue(value1) && cell.isValid()){

			return;
		}

		cell.setText( value1<0?"":DisplayFormatters.formatByteCountToKiBEtc( value1 ) + (value2==-1?"":(" (" + DisplayFormatters.formatByteCountToKiBEtc( value2 ) + ")")));
	}
}
