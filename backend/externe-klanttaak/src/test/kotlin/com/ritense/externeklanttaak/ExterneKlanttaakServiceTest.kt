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

package com.ritense.externeklanttaak


internal class ExterneKlanttaakServiceTest {
//
//    private lateinit var objectManagementService: ObjectManagementService
//    private lateinit var pluginService: PluginService
//    private lateinit var objectenApiPlugin: ObjectenApiPlugin
//    private lateinit var objecttypenApiPlugin: ObjecttypenApiPlugin
//    private lateinit var valueResolverService: ValueResolverService
//    private lateinit var taskService: CamundaTaskService
//    private lateinit var utilService: DefaultUtilityService
//    private lateinit var objectManagement: ObjectManagement
//
//    @BeforeEach
//    fun init() {
//        objectManagementService = mock()
//        pluginService = mock()
//        objectenApiPlugin = mock()
//        objecttypenApiPlugin = mock()
//        valueResolverService = mock()
//        taskService = mock()
//        utilService = mock()
//
//        objectManagement = ObjectManagement(
//            id = UUID.randomUUID(),
//            title = "klanttaak",
//            objectenApiPluginConfigurationId = UUID.randomUUID(),
//            objecttypenApiPluginConfigurationId = UUID.randomUUID(),
//            objecttypeId = "object-type",
//            objecttypeVersion = 1,
//        )
//    }
//
//    @Test
//    fun `should create klanttaak`() {
//        // given
//        val externeKlanttaakService =
//            ExterneKlanttaakService(
//                objectManagementService,
//                pluginService,
//                valueResolverService,
//                taskService,
//                utilService
//            )
//
//        val klanttaakVersion = ExterneKlanttaakVersion.V1_1_0
//        val config = CreateTaakActionV1x1x0(
//            taakSoort = URL,
//            url = "pv:external-url",
//            taakReceiver = OTHER,
//            identificationKey = "bsn",
//            identificationValue = "999990755",
//            verloopdatum = "01-01-2025"
//        )
//        val executionFake = DelegateExecutionFake()
//            .withProcessInstanceId("processInstanceId")
//        val taskFake = DelegateTaskFake()
//            .withName("Fake Task")
//            .withId("fake-task-id")
//            .withExecution(executionFake)
//            .apply {
//                dueDate = Date.from(Instant.from(LocalDate.parse("2024-12-24").atStartOfDay(ZoneOffset.UTC)))
//            }
//
//        whenever(objectManagementService.getById(objectManagement.id))
//            .thenReturn(objectManagement)
//        whenever(pluginService.createInstance<ObjectenApiPlugin>(objectManagement.objectenApiPluginConfigurationId))
//            .thenReturn(objectenApiPlugin)
//        whenever(pluginService.createInstance<ObjecttypenApiPlugin>(objectManagement.objecttypenApiPluginConfigurationId))
//            .thenReturn(objecttypenApiPlugin)
//        whenever(objecttypenApiPlugin.getObjectTypeUrlById(any()))
//            .thenReturn(URI.create("https://example.com/objecttypen/api/v1/${objectManagement.objecttypeId}"))
//        whenever(
//            valueResolverService.resolveValues(
//                any(),
//                any(),
//                any()
//            )
//        ).thenReturn(mapOf("pv:external-url" to externalUrl))
//
//        // when
//        externeKlanttaakService.createExterneKlanttaak(
//            klanttaakVersion = klanttaakVersion,
//            objectManagementId = objectManagement.id,
//            delegateTask = taskFake,
//            config = config
//        )
//
//        // then
//        val requestCaptor: KArgumentCaptor<ObjectRequest> = argumentCaptor()
//        verify(objectenApiPlugin, times(1)).createObject(requestCaptor.capture())
//
//        val createdKlanttaak: ExterneKlanttaakV1x1x0 = objectMapper.treeToValue(requestCaptor.firstValue.record.data!!)
//        assertEquals(taskFake.name, createdKlanttaak.titel)
//        assertEquals(bsn, createdKlanttaak.identificatie.value)
//        assertEquals(externalUrl, createdKlanttaak.url!!.url)
//    }
//
//    @Test
//    fun `should create klanttaak and store resultUrl`() {
//        // given
//        val externeKlanttaakService =
//            ExterneKlanttaakService(
//                objectManagementService,
//                pluginService,
//                valueResolverService,
//                taskService,
//                utilService
//            )
//
//        val klanttaakVersion = ExterneKlanttaakVersion.V1_1_0
//        val config = CreateTaakActionV1x1x0(
//            taakSoort = URL,
//            url = "pv:external-url",
//            taakReceiver = OTHER,
//            identificationKey = "bsn",
//            identificationValue = "999990755",
//            verloopdatum = "01-01-2025",
//            resultingKlanttaakObjectUrlVariable = "klanttaakUrl",
//        )
//        val executionFake = DelegateExecutionFake()
//            .withProcessInstanceId("processInstanceId")
//        val taskFake = DelegateTaskFake()
//            .withName("Fake Task")
//            .withId("fake-task-id")
//            .withExecution(executionFake)
//            .apply {
//                dueDate = Date.from(Instant.from(LocalDate.parse("2024-12-24").atStartOfDay(ZoneOffset.UTC)))
//            }
//        val klanttaakWrapped: ObjectWrapper = objectMapper.treeToValue(openTaakObject)
//
//        whenever(objectManagementService.getById(objectManagement.id))
//            .thenReturn(objectManagement)
//        whenever(pluginService.createInstance<ObjectenApiPlugin>(objectManagement.objectenApiPluginConfigurationId))
//            .thenReturn(objectenApiPlugin)
//        whenever(pluginService.createInstance<ObjecttypenApiPlugin>(objectManagement.objecttypenApiPluginConfigurationId))
//            .thenReturn(objecttypenApiPlugin)
//        whenever(objecttypenApiPlugin.getObjectTypeUrlById(any()))
//            .thenReturn(URI.create("https://example.com/objecttypen/api/v1/${objectManagement.objecttypeId}"))
//        val requestCaptor: KArgumentCaptor<ObjectRequest> = argumentCaptor()
//        whenever(objectenApiPlugin.createObject(requestCaptor.capture())).thenReturn(klanttaakWrapped)
//        whenever(
//            valueResolverService.resolveValues(
//                any(),
//                any(),
//                any()
//            )
//        ).thenReturn(mapOf("pv:external-url" to externalUrl))
//
//        // when
//        externeKlanttaakService.createExterneKlanttaak(
//            klanttaakVersion = klanttaakVersion,
//            objectManagementId = objectManagement.id,
//            delegateTask = taskFake,
//            config = config
//        )
//
//        // then
//        verify(objectenApiPlugin, times(1)).createObject(any())
//
//        val createdKlanttaak: ExterneKlanttaakV1x1x0 = objectMapper.treeToValue(requestCaptor.firstValue.record.data!!)
//        assertEquals(taskFake.name, createdKlanttaak.titel)
//        assertEquals(bsn, createdKlanttaak.identificatie.value)
//        assertEquals(externalUrl, createdKlanttaak.url!!.url)
//        assertTrue(executionFake.variables.containsKey("klanttaakUrl"))
//        assertEquals(klanttaakWrapped.url, executionFake.getVariable("klanttaakUrl"))
//    }
//
//    @Test
//    fun `should complete existing klanttaak`() {
//        // given
//        val externeKlanttaakService =
//            ExterneKlanttaakService(
//                objectManagementService,
//                pluginService,
//                valueResolverService,
//                taskService,
//                utilService
//            )
//
//        val klanttaakVersion = ExterneKlanttaakVersion.V1_1_0
//        val config = CompleteTaakActionV1x1x0(
//            koppelDocumenten = true,
//            bewaarIngediendeGegevens = true,
//        )
//        val executionFake = DelegateExecutionFake()
//            .withProcessInstanceId("processInstanceId")
//            .withVariables(
//                mapOf(
//                    "verwerkerTaakId" to "taak-id",
//                    "externeKlanttaakObjectUrl" to objectUrl
//                )
//            )
//        val afgerondeKlanttaakWrapped: ObjectWrapper = objectMapper.treeToValue(afgerondeTaakObject)
//        val verwerkteKlanttaakWrapped: ObjectWrapper = objectMapper.treeToValue(afgerondeTaakObject)
//
//        whenever(objectManagementService.getById(objectManagement.id))
//            .thenReturn(objectManagement)
//        whenever(pluginService.createInstance<ObjectenApiPlugin>(objectManagement.objectenApiPluginConfigurationId))
//            .thenReturn(objectenApiPlugin)
//        whenever(pluginService.createInstance<ObjecttypenApiPlugin>(objectManagement.objecttypenApiPluginConfigurationId))
//            .thenReturn(objecttypenApiPlugin)
//
//        val uriCaptor: KArgumentCaptor<URI> = argumentCaptor()
//        val requestCaptor: KArgumentCaptor<ObjectRequest> = argumentCaptor()
//
//        whenever(objectenApiPlugin.getObject(uriCaptor.capture()))
//            .thenReturn(afgerondeKlanttaakWrapped)
//        whenever(objectenApiPlugin.objectPatch(any(), requestCaptor.capture()))
//            .thenReturn(verwerkteKlanttaakWrapped)
//        whenever(objecttypenApiPlugin.getObjectTypeUrlById(any()))
//            .thenReturn(URI(objecttypeUrl))
//        whenever(
//            valueResolverService.resolveValues(
//                any(),
//                any(),
//                any()
//            )
//        ).thenReturn(mapOf("pv:externeKlanttaakObjectUrl" to objectUrl))
//
//        // when
//        externeKlanttaakService.completeExterneKlanttaak(
//            klanttaakVersion = klanttaakVersion,
//            objectManagementId = objectManagement.id,
//            config = config,
//            execution = executionFake,
//        )
//
//        // then
//        verify(objectenApiPlugin, times(1)).getObject(any())
//        verify(objectenApiPlugin, times(1)).objectPatch(any(), any())
//
//        val patchedKlanttaak: ExterneKlanttaakV1x1x0 = objectMapper.treeToValue(requestCaptor.firstValue.record.data!!)
//        assertEquals(objectUrl, uriCaptor.firstValue.toString())
//        assertEquals("https://example.com/", patchedKlanttaak.url!!.url)
//        assertEquals(TaakStatus.VERWERKT, patchedKlanttaak.status)
//    }
//
//    companion object {
//        private val objectMapper = MapperSingleton.get()
//    }
}