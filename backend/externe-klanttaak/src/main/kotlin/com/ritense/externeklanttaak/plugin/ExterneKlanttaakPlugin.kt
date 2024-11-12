/*
 * Copyright 2015-2024 Ritense BV, the Netherlands.
 *
 * Licensed under EUPL, Version 1.2 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ritense.externeklanttaak.plugin

import com.ritense.externeklanttaak.domain.ExterneKlanttaakVersion
import com.ritense.externeklanttaak.domain.IPluginActionConfig
import com.ritense.externeklanttaak.service.ExterneKlanttaakService
import com.ritense.notificatiesapi.NotificatiesApiPlugin
import com.ritense.plugin.annotation.Plugin
import com.ritense.plugin.annotation.PluginAction
import com.ritense.plugin.annotation.PluginActionProperty
import com.ritense.plugin.annotation.PluginProperty
import com.ritense.processlink.domain.ActivityTypeWithEventName
import mu.withLoggingContext
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.DelegateTask
import java.util.UUID

@Plugin(
    key = "externeklanttaak",
    title = "Externe Klanttaak Plugin",
    description = "Lets you create and handle Externe Klanttaak specification compliant Objects"
)
class ExterneKlanttaakPlugin(
    private val externeKlanttaakService: ExterneKlanttaakService,
    private val supportedExterneKlanttaakVersions: List<ExterneKlanttaakVersion>,
) {
    @PluginProperty(key = "objectManagementConfigurationId", secret = false)
    internal lateinit var objectManagementConfigurationId: UUID

    @PluginProperty(key = "klanttaakVersion", secret = false)
    internal lateinit var externeKlanttaakVersion: ExterneKlanttaakVersion

    @PluginProperty(key = "notificatiesApiPluginConfiguration", secret = false)
    internal lateinit var notificatiesApiPluginConfiguration: NotificatiesApiPlugin

    @PluginProperty(key = "finalizerProcess", secret = false)
    internal lateinit var finalizerProcess: String

    @PluginAction(
        key = "create-externeklanttaak",
        title = "Create Externe Klanttaak",
        description = "Create a task for a portal by storing it in the Objecten-API",
        activityTypes = [ActivityTypeWithEventName.USER_TASK_CREATE]
    )
    fun createExterneKlanttaak(
        delegateTask: DelegateTask,
        @PluginActionProperty config: IPluginActionConfig,
    ) {
        require(externeKlanttaakVersion in supportedExterneKlanttaakVersions)
        require(externeKlanttaakVersion supports config::class) {
            "Externe Klanttaak Plugin version [$externeKlanttaakVersion] does not support the invoked Plugin Action."
        }
        withLoggingContext(DelegateTask::class.java.canonicalName to delegateTask.id) {
            externeKlanttaakService.createExterneKlanttaak(
                config = config,
                klanttaakVersion = externeKlanttaakVersion,
                objectManagementId = objectManagementConfigurationId,
                delegateTask = delegateTask,
            )
        }
    }

    @PluginAction(
        key = "complete-externeklanttaak",
        title = "Complete Externe Klanttaak",
        description = "Complete portal task and update status on Objects Api",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START]
    )
    fun completeExterneKlanttaak(
        execution: DelegateExecution,
        @PluginActionProperty config: IPluginActionConfig,
    ) {
        require(externeKlanttaakVersion in supportedExterneKlanttaakVersions)
        require(externeKlanttaakVersion supports config::class) {
            "Externe Klanttaak Plugin version [$externeKlanttaakVersion] does not support the invoked Plugin Action."
        }
        withLoggingContext(DelegateExecution::class.java.canonicalName to execution.id) {
            externeKlanttaakService.completeExterneKlanttaak(
                config = config,
                klanttaakVersion = externeKlanttaakVersion,
                objectManagementId = objectManagementConfigurationId,
                execution = execution,
            )
        }
    }

}