/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.tools.ant.types.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.types.Resource;

/**
 * ResourceCollection that contains all resources of another
 * collection except for the first <code>count</code> elements, a la
 * the UNIX tail command with parameter <code>-n +count</code>.
 * @since Ant 1.9.5
 */
public class AllButFirst extends SizeLimitCollection {

    /**
     * Take the first <code>count</code> elements.
     * @return a Collection of Resources.
     */
    protected Collection<Resource> getCollection() {
        int ct = getValidCount();
        Iterator<Resource> iter = getResourceCollection().iterator();
        List<Resource> al = new ArrayList<Resource>();
        for (int i = 0; i < ct && iter.hasNext(); i++) {
            // discard
            iter.next();
        }
        while (iter.hasNext()) {
            al.add(iter.next());
        }
        return al;
    }

    @Override
    public synchronized int size() {
        int sz = getResourceCollection().size();
        int ct = getValidCount();
        return sz > ct ? sz - ct : 0;
    }

}
