package org.danwatt.hibernate.embedded.types;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.*;
import java.time.MonthDay;

@SuppressWarnings("unused")
public class MonthDayType extends ImmutableType<MonthDay> {
	public MonthDayType() {
		super(MonthDay.class);
	}

	@Override
	public int[] sqlTypes() {
		return new int[] {Types.DATE};
	}

	@Override
	public MonthDay get(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
		Date value = rs.getDate(names[0]);
		return value == null ? null : MonthDay.from(value.toLocalDate());
	}

	@Override
	public void set(PreparedStatement st, MonthDay value, int index, SharedSessionContractImplementor session) throws SQLException {
		if (value == null) {
			st.setNull(index, Types.DATE);
		} else {
			st.setDate(index, Date.valueOf(value.atYear(1970)));
		}
	}
}
