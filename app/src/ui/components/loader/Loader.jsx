import React, { Component } from 'react'
import { Punk } from './../../../assets'
import './Loader.css'

export class Loader extends Component {

    render() {
        return (
            <div className="loader-container">
                <span className="loader-titulo">Carregando...</span>
                <img src={Punk} alt="punk correndo sem sair do lugar" />
            </div>
        )
    }

}

export default Loader;
