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

import java.text.DecimalFormat;

public record Repository(String name, int stargazers_count, int forks_count, int watchers_count) {

	public String toString() {
		return "Project " + name() + ": " +
				DecimalFormat.getInstance().format(stargazers_count) + " stars, " +
				DecimalFormat.getInstance().format(forks_count) + " forks, " +
				DecimalFormat.getInstance().format(watchers_count) + " watchers";
	}

}
