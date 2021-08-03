package com.sort.sortcore.data;

import java.time.Instant;
import java.util.UUID;

public class MockData {
	UUID id;
	Instant createTime;

	MockData(final UUID id, final Instant createTime) {
		this.id = id;
		this.createTime = createTime;
	}

	public static MockData$MockDataBuilder builder() {
		return new MockData$MockDataBuilder();
	}

	public UUID getId() {
		return this.id;
	}

	public Instant getCreateTime() {
		return this.createTime;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public void setCreateTime(final Instant createTime) {
		this.createTime = createTime;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof MockData)) {
			return false;
		} else {
			MockData other = (MockData) o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$id = this.getId();
				Object other$id = other.getId();
				if (this$id == null) {
					if (other$id != null) {
						return false;
					}
				} else if (!this$id.equals(other$id)) {
					return false;
				}

				Object this$createTime = this.getCreateTime();
				Object other$createTime = other.getCreateTime();
				if (this$createTime == null) {
					if (other$createTime != null) {
						return false;
					}
				} else if (!this$createTime.equals(other$createTime)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof MockData;
	}

	@Override
	public int hashCode() {
		int PRIME = 0;
		int result = 1;
		Object $id = this.getId();
		result = result * 59 + ($id == null ? 43 : $id.hashCode());
		Object $createTime = this.getCreateTime();
		result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
		return result;
	}

	@Override
	public String toString() {
		UUID var10000 = this.getId();
		return "MockData(id=" + var10000 + ", createTime=" + this.getCreateTime() + ")";
	}
}