/*
 * Copyright 2014 JBoss by Red Hat.
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

package org.jbpm.executor.impl.mem;

import javax.enterprise.inject.Produces;

import org.jbpm.executor.ExecutorServiceFactory;
import org.kie.internal.executor.api.ExecutorAdminService;
import org.kie.internal.executor.api.ExecutorQueryService;
import org.kie.internal.executor.api.ExecutorService;
import org.kie.internal.executor.api.ExecutorStoreService;
import org.kie.internal.runtime.cdi.Activate;

@Activate(whenNotAvailable="org.jbpm.runtime.manager.impl.RuntimeManagerFactoryImpl")
public class InMemoryExecutorServiceProducer {

	@Produces
	public ExecutorService produceExecutorService() {
		ExecutorService service = ExecutorServiceFactory.newExecutorService();
		
		return service;
	}

	@Produces
	public ExecutorStoreService produceStoreService() {
		ExecutorStoreService storeService = new InMemoryExecutorStoreService(true);
		
		return storeService;
	}

	@Produces
	public ExecutorAdminService produceAdminService() {
		ExecutorAdminService adminService = new InMemoryExecutorAdminServiceImpl(true);
		InMemoryExecutorStoreService storeService = new InMemoryExecutorStoreService(true);
		
		((InMemoryExecutorAdminServiceImpl) adminService).setStoreService(storeService);
		
		return adminService;
	}

	@Produces
	public ExecutorQueryService produceQueryService() {
		ExecutorQueryService queryService = new InMemoryExecutorQueryServiceImpl(true);
		InMemoryExecutorStoreService storeService = new InMemoryExecutorStoreService(true);		
		((InMemoryExecutorQueryServiceImpl) queryService).setStoreService(storeService);
		
		return queryService;
	}

}
