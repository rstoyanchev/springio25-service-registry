/*
 * Copyright 2002-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.github;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.jspecify.annotations.Nullable;

public record Milestone(
		int number, String title, int open_issues, int closed_issues, @Nullable ZonedDateTime due_on)
		implements Comparable<Milestone> {

	public boolean hasDueDate() {
		return (this.due_on != null);
	}

	@Override
	public int compareTo(Milestone other) {
		return other.title.compareTo(title);
	}

	public String toString() {
		return "Milestone " + title + ", " +
				"opened " + open_issues + ", closed " + closed_issues +
				(hasDueDate() ? ", due on " + due_on.format(DateTimeFormatter.ISO_LOCAL_DATE) : "");
	}

}
