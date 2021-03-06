/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.model.internal.core;

import org.gradle.api.Nullable;
import org.gradle.model.internal.core.rule.describe.ModelRuleDescriptor;
import org.gradle.model.internal.type.ModelType;

import java.util.Set;

public interface MutableModelNode {
    ModelPath getPath();

    @Nullable
    <T> ModelView<? extends T> asWritable(ModelType<T> type, ModelRuleDescriptor ruleDescriptor, @Nullable Inputs inputs);

    @Nullable
    <T> ModelView<? extends T> asReadOnly(ModelType<T> type, @Nullable ModelRuleDescriptor ruleDescriptor);

    /**
     * Adds an element to the graph, linked from this element. The given creator is used to initialize the element when required.
     */
    MutableModelNode addLink(ModelCreator creator);

    /**
     * Removes an element linked from this element from the graph.
     */
    void removeLink(String name);

    /**
     * Applies an action to this element.
     */
    <T> void applyToSelf(ModelActionRole type, ModelAction<T> action);

    /**
     * Applies an action to all elements linked from this element.
     */
    <T> void applyToAllLinks(ModelActionRole type, ModelAction<T> action);

    /**
     * Applies an action to a linked element.
     */
    <T> void applyToLink(ModelActionRole type, ModelAction<T> action);

    @Nullable
    MutableModelNode getLink(String name);

    int getLinkCount(ModelType<?> type);

    Set<String> getLinkNames(ModelType<?> type);

    Iterable<MutableModelNode> getLinks(ModelType<?> type);

    boolean hasLink(String name);

    boolean hasLink(String name, ModelType<?> type);

    <T> void setPrivateData(ModelType<T> type, T object);

    <T> T getPrivateData(ModelType<T> type);

    /**
     * Ensure that the views are available, with default values applied.
     */
    void ensureUsable();

    boolean isMutable();
}
