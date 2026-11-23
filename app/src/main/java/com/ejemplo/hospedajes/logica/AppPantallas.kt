package com.ejemplo.hospedajes.logica

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ejemplo.hospedajes.data.usuarioEjemplo
import com.ejemplo.hospedajes.navegacion.Rutas
import com.ejemplo.hospedajes.navegacion.esNivelSuperior
import com.ejemplo.hospedajes.navegacion.irASeccion
import com.ejemplo.hospedajes.ui.AddEditHotelDialog
import com.ejemplo.hospedajes.ui.BarraInferior
import com.ejemplo.hospedajes.ui.BarraSuperior
import com.ejemplo.hospedajes.ui.ConfirmDeleteDialog
import com.ejemplo.hospedajes.ui.ContenidoMenuLateral
import com.ejemplo.hospedajes.ui.PantallaAcercaDe
import com.ejemplo.hospedajes.ui.PantallaDetalle
import com.ejemplo.hospedajes.ui.PantallaExplorar
import com.ejemplo.hospedajes.ui.PantallaInicio
import com.ejemplo.hospedajes.ui.PantallaPrincipal
import kotlinx.coroutines.launch

@Composable
fun AppPantallas(
    viewModel: HospedajeViewModel = viewModel()
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Pantalla actual (UD4, apartado 6.1)
    val entrada by navController.currentBackStackEntryAsState()
    val destino = entrada?.destination
    val esNivelSuperior = destino.esNivelSuperior()

    // Hotel del detalle, para el título de la barra
    val hotelIdActual = entrada?.arguments?.getString("hotelId")
    val titulo = when (destino?.route) {
        Rutas.INICIO -> "Inicio"
        Rutas.MIS_HOTELES -> "Mis hoteles"
        Rutas.EXPLORAR -> "Explorar"
        Rutas.ACERCA_DE -> "Acerca de"
        Rutas.DETALLE -> uiState.hotelList.find { it.id == hotelIdActual }?.name ?: "Detalle"
        else -> ""
    }

    // Mensajes del Snackbar (UD3, apartado 8.2)
    LaunchedEffect(uiState.userMessage) {
        uiState.userMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.onUserMessageShown()
        }
    }

    // Menú lateral (UD4, apartado 7)
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = esNivelSuperior,
        drawerContent = {
            ContenidoMenuLateral(
                usuario = usuarioEjemplo,
                destino = destino,
                onSeccionClick = { seccion ->
                    scope.launch { drawerState.close() }
                    navController.irASeccion(seccion.ruta)
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                BarraSuperior(
                    titulo = titulo,
                    esNivelSuperior = esNivelSuperior,
                    onAbrirMenu = { scope.launch { drawerState.open() } },
                    onVolver = { navController.navigateUp() },
                    onAcercaDe = { navController.irASeccion(Rutas.ACERCA_DE) }
                )
            },
            bottomBar = {
                if (esNivelSuperior) {
                    BarraInferior(
                        destino = destino,
                        numHoteles = uiState.hotelList.size,
                        onSeccionClick = { seccion -> navController.irASeccion(seccion.ruta) }
                    )
                }
            },
            floatingActionButton = {
                if (destino?.route == Rutas.MIS_HOTELES) {
                    FloatingActionButton(onClick = { viewModel.onAddHotelClicked() }) {
                        Icon(Icons.Default.Add, contentDescription = "Añadir hospedaje")
                    }
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Rutas.INICIO,
                modifier = Modifier.padding(innerPadding)
            ) {
                // Destinos (UD4, apartados 4.6, 7 y 8)
                composable(Rutas.INICIO) {
                    PantallaInicio(
                        nombreUsuario = usuarioEjemplo.nombre,
                        numHoteles = uiState.hotelList.size,
                        onVerMisHoteles = { navController.irASeccion(Rutas.MIS_HOTELES) }
                    )
                }
                composable(Rutas.MIS_HOTELES) {
                    PantallaPrincipal(
                        hoteles = uiState.hotelList,
                        onHotelClick = { hotel -> navController.navigate(Rutas.detalle(hotel.id)) }
                    )
                }
                composable(Rutas.EXPLORAR) {
                    PantallaExplorar()
                }
                composable(Rutas.ACERCA_DE) {
                    PantallaAcercaDe()
                }
                composable(
                    route = Rutas.DETALLE,
                    arguments = listOf(navArgument("hotelId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val hotelId = backStackEntry.arguments?.getString("hotelId")
                    val hotel = uiState.hotelList.find { it.id == hotelId }
                    if (hotel != null) {
                        PantallaDetalle(
                            hotel = hotel,
                            onEditar = { viewModel.onEditHotelClicked(hotel) },
                            onBorrar = { viewModel.onDeleteHotelClicked(hotel) }
                        )
                    } else {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("El hospedaje no existe")
                        }
                    }
                }
            }

            // Atrás cierra el menú lateral si está abierto (UD4, apartado 7.2)
            BackHandler(enabled = drawerState.isOpen) {
                scope.launch { drawerState.close() }
            }
        }
    }

    // Diálogos (UD3, apartado 7): se dibujan encima de la pantalla visible
    uiState.hotelToDelete?.let { hotel ->
        ConfirmDeleteDialog(
            hotel = hotel,
            onDismiss = { viewModel.onDismissDeleteDialog() },
            onConfirm = {
                viewModel.onConfirmDeleteHotel()
                navController.popBackStack()   // vuelve del detalle a la lista
            }
        )
    }

    if (uiState.isAddDialogOpen) {
        AddEditHotelDialog(
            onDismiss = { viewModel.onDismissAddDialog() },
            onConfirm = { name, city, province, phone, image ->
                viewModel.onInsertHotel(name, city, province, phone, image)
            }
        )
    }

    uiState.hotelToEdit?.let { hotel ->
        AddEditHotelDialog(
            hotelToEdit = hotel,
            onDismiss = { viewModel.onDismissEditDialog() },
            onConfirm = { name, city, province, phone, image ->
                viewModel.onUpdateHotel(hotel.id, name, city, province, phone, image)
            }
        )
    }
}
