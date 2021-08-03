package com.sort.sortcore.data;

import java.time.Instant;
import java.util.UUID;

public class MockData$MockDataBuilder {
	private UUID id;
	private Instant createTime;

	MockData$MockDataBuilder() {
	}

	public MockData$MockDataBuilder id(final UUID id) {
		this.id = id;
		return this;
	}

	public MockData$MockDataBuilder createTime(final Instant createTime) {
		this.createTime = createTime;
		return this;
	}

	public MockData build() {
		return new MockData(this.id, this.createTime);
	}

	@Override
	public String toString() {
		return "MockData.MockDataBuilder(id=" + this.id + ", createTime=" + this.createTime + ")";
	}
}