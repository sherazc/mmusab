import React from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router';
import { AppNavigationLayout } from './navigation/AppNavigationLayout';
import { Home } from './component/Home';
import { Login } from './component/Login';

function App() {
  return (
        <BrowserRouter>
      <Routes>
        <Route path='/' element={<AppNavigationLayout/>}>
          <Route index element={<Home/>}></Route>
          <Route path='/login' element={<Login/>}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
