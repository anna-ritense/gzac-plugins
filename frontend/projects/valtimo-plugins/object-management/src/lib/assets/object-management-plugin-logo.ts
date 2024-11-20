/*
 * Copyright 2015-2022 Ritense BV, the Netherlands.
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

const OBJECT_MANAGEMENT_PLUGIN_LOGO_BASE64 =
    'data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyBpZD0iTGF5ZXJfMiIgZGF0YS1uYW1lPSJMYXllciAyIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAzNDMuMDggMTgwLjIxIj4KICA8ZGVmcz4KICAgIDxzdHlsZT4KICAgICAgLmNscy0xIHsKICAgICAgICBmaWxsOiAjZmZiYzJkOwogICAgICB9CiAgICA8L3N0eWxlPgogIDwvZGVmcz4KICA8ZyBpZD0iTGF5ZXJfMS0yIiBkYXRhLW5hbWU9IkxheWVyIDEiPgogICAgPHBhdGggY2xhc3M9ImNscy0xIiBkPSJtMTQyLjgxLDcxLjgxYy04LjAyLTcuNjUtMTcuMzEtMTIuODQtMjguNTgtMTMuMDUtMTQuMDItLjI2LTMwLjQ4LDEwLjExLTMzLjQzLDI3LjM2LTEuNDIsOC4yOS42NSwxNS45NCw1LjY4LDIyLjYyLDcuNTEsOS45OCwxNy42NSwxNS41MywzMC4xNCwxNS41OSwxMi4yMS4wNiwyMy4yNC00LjA2LDMyLjk4LTExLjUsMi45OS0yLjI4LDUuNzgtNC43Nyw4LjM4LTcuNDguNi0uNjMsMS4yMi0xLjI2LDEuOTItMS43OC44OS0uNjUsMS44OS0uNzksMi42Ny4xNy43Ljg3LjE2LDEuNjYtLjQzLDIuMzMtMy42NCw0LjEzLTcuNDgsOC4wNC0xMS44MywxMS40NS0xMC4zNyw4LjE0LTIyLjE5LDEyLjYzLTM1LjI2LDEyLjgzLTIxLjAzLjMyLTQxLjIyLTE1LjY2LTQzLjctMzYuNTgtMS45NS0xNi41NCw0LjcxLTI5LjI4LDE3LjU3LTM4LjkxLDE1LjQ3LTExLjU3LDMyLjM3LTExLjgyLDQ5LjY4LTQuNjcsMTcuMTUsNy4wOCwzMC4yMywxOS4xNiw0MC43LDM0LjI1LDcuMSwxMC4yMywxNS4yMywxOS40NywyNS4zNiwyNi44Miw2LjE3LDQuNDgsMTIuOTUsNy41NywyMC41NCw4LjQ4LDExLjE0LDEuMzQsMjUuNjYtNC4xNCwzMi40Ny0xNi42Miw2LjItMTEuMzYsNC4zNi0yNS41Mi00LjU1LTM1LjEyLTkuNTktMTAuMzMtMjEuMzktMTQuMDMtMzUuMjctMTEuOTMtMTIuNiwxLjkxLTIzLjAzLDcuOC0zMi4wMywxNi41NS0uNzIuNy0xLjM3LDEuNDYtMi4xMywyLjExLTEuMDQuODktMS45OCwyLjk5LTMuNTYsMS41MS0xLjM1LTEuMjguMS0yLjc3LDEuMDUtMy44NywxMC40NS0xMi4wOCwyMi45OC0yMC42MiwzOS4xNS0yMi45OSwxOC4wNi0yLjY1LDMyLjgxLDMuMzQsNDMuODUsMTcuNjUsMTMuMDMsMTYuODksMTAuNDEsNDMuOTEtOS44Myw1OC4zNC0xNS4yMiwxMC44NS0zMS43MiwxMS44MS00OC43OSw1LjM4LTE2LjUyLTYuMjMtMjkuMjYtMTcuNDktMzkuNTEtMzEuNjItNS41Ny03LjY5LTEwLjg5LTE1LjU1LTE3LjYtMjIuMzNsLTUuNjItNS4wMloiLz4KICAgIDxwYXRoIGQ9Im0zMjMuNDYsODkuNjVjLTEuMy0uMzUtMi42MS0uNy0zLjkxLTEuMDUtMTEuOTEtMy4yMy0xOC4zMS0xMS4yNi0xOC41LTIzLjYyLS4xNi05Ljg3LS4wMy0xOS43NS0uMDYtMjkuNjIsMC0xLjg3LjAzLTMuNzctLjI4LTUuNjEtMS41NS04Ljk4LTcuNzUtMTQuMzEtMTcuNzItMTUuMzlxLTQuODItLjUyLTQuODItNS4zOWMwLTIuMjUuMDYtNC41LS4wMi02Ljc1LS4wNS0xLjQ4LjItMi4yNSwyLTIuMjEsOC41MS4xNywxNi42MywxLjgxLDIzLjkyLDYuMzgsOS43OCw2LjEzLDEzLjg0LDE1LjM5LDEzLjk3LDI2LjU5LjExLDkuNS4wMiwxOSwuMDMsMjguNSwwLDEuNjItLjAxLDMuMjUuMDksNC44Ny40NSw3LjI0LDQuMDksMTIuMTUsMTAuOTIsMTQuNiwzLjU1LDEuMjcsNy4yNywxLjg1LDExLjAyLDEuOSwyLjE1LjAzLDMuMTUuNTUsMi45NSwyLjk5LS4yNSwzLjEtLjE0LDYuMjUtLjAzLDkuMzcuMDYsMS42My0uNTUsMi4yLTIuMTQsMi4yMS0zLjUuMDItNi45Ni40MS0xMC4zNCwxLjQ3LTguMjksMi42Mi0xMi4yNCw3LjUtMTIuNCwxNi4xNC0uMTgsMTAtLjA5LDIwLS4wNywzMCwwLDMuNTEtLjIsNy4wMS0uOTQsMTAuNDMtMi4xNSw5Ljg4LTguMTUsMTYuNjUtMTcuMjgsMjAuNy02LjAxLDIuNjctMTIuMzksMy42NC0xOC45Myw0LjA0LTIuNS4xNS0yLjk1LS43NC0yLjgxLTIuOTIuMTctMi42MS4wMy01LjI1LjA0LTcuODcuMDItMy4yNy4wMy0zLjMyLDMuMTYtMy40OCwyLjM5LS4xMiw0LjcxLS41Niw2Ljk4LTEuMjUsOC4yNi0yLjUzLDEyLjQ2LTcuODIsMTIuNjEtMTYuNDguMTgtMTAuMzcuMS0yMC43NS4wNi0zMS4xMi0uMDEtMy4xNS4yNy02LjI1LDEtOS4yOSwxLjkyLTguMDQsNy4zNC0xMi43OSwxNC45MS0xNS4zNywyLjE4LS43NCw0LjQyLTEuMyw2LjYzLTEuOTQtLjAyLS4yOC0uMDQtLjU1LS4wNS0uODNaIi8+CiAgICA8cGF0aCBkPSJtMTkuMjYsOTAuNDdjMi4xLjYyLDQuMjMsMS4xNyw2LjMsMS44OSw5Ljk4LDMuNDMsMTUuNjIsMTAuNjMsMTUuOTcsMjEuMi4zMywxMC4yNC4xNCwyMC40OS4yMywzMC43My4wMiwyLjI0LjE1LDQuNS40NCw2LjcyLjc3LDUuOTEsMy45LDEwLjE1LDkuMzgsMTIuNTYsMy4zNCwxLjQ3LDYuODcsMi4zNywxMC41MiwyLjMxLDIuMzUtLjA0LDMuMDEuODUsMi44NywzLjA4LS4xOCwyLjk5LS4wOCw2LS4wMyw4Ljk5LjAzLDEuNDItLjEsMi4yOS0xLjk2LDIuMjMtOC4wOS0uMjYtMTUuOTEtMS42Mi0yMy4wNC01LjYyLTkuNi01LjM5LTE0LjY2LTEzLjc2LTE0Ljk1LTI0LjcxLS4yOC0xMC4zNi0uMDktMjAuNzQtLjEtMzEuMTEsMC0yLjM4LS4xNC00LjczLS41Ni03LjA4LS45OS01LjYyLTQuMDctOS41My05LjI5LTExLjc4LTMuODMtMS42Ni03Ljg3LTIuNDYtMTIuMDItMi40Mi0yLjIuMDItMy4xNi0uNTUtMy0yLjk1LjIxLTMuMTEuMTMtNi4yNS4wMi05LjM3LS4wNi0xLjY1LjU4LTIuMTYsMi4xNC0yLjI1LDIuNjEtLjE0LDUuMjEtLjMyLDcuOC0uOSwxMC4xMi0yLjI1LDE0LjczLTcuNjYsMTQuODYtMTguMDcuMTMtMTAuNDkuMDYtMjAuOTkuMDMtMzEuNDktLjAxLTQuNjguOC05LjE4LDIuNzItMTMuNDYsMy4wMS02LjY4LDguMTYtMTEuMjQsMTQuNjQtMTQuMzlDNDguNjIsMS40Nyw1NS40NC4yNCw2Mi41MSwwYzIuMS0uMDcsMi41Mi42NywyLjQ0LDIuNTMtLjEyLDIuOTktLjE2LDYsLjAxLDguOTkuMTEsMi4wMi0uNjcsMi42Ni0yLjU2LDIuNjgtMi44OS4wMy01LjY5LjYyLTguNDMsMS41Ny03LjA2LDIuNDQtMTEuMTMsNy4zLTExLjksMTQuNzgtLjE5LDEuODYtLjMyLDMuNzMtLjMzLDUuNi0uMDYsOS44Ny4xMywxOS43NS0uMTYsMjkuNjEtLjM2LDEyLjA4LTYuNjgsMTkuNjgtMTguNCwyMi44NC0xLjMuMzUtMi42MS43MS0zLjkxLDEuMDYsMCwuMjctLjAyLjUzLS4wMi44WiIvPgogIDwvZz4KPC9zdmc+';

export {OBJECT_MANAGEMENT_PLUGIN_LOGO_BASE64};