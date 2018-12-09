import React, { Component } from 'react'
import { HoleInTheWall, Crescer } from './../../../assets'

import './Base.css'

export class Base extends Component {

    render() {
        return (
            <div className={`container ${this.props.classeAdicional ? this.props.classeAdicional : ''}`}>
                <div className="container-titulo">
                    <img className="base-logo-jogo" src={HoleInTheWall} alt="logo do jogo" />
                </div>
                <div className="container-meio">
                    <div className={`container-muro ${this.props.containerMuroTamanho}`}>
                        <div className="buraco">
                            {this.props.form}
                        </div>
                    </div>
                    {this.props.children}
                </div>
                <div className="container-baixo">
                    <img className="base-logo-crescer" src={Crescer} alt="logo do Crescer" />
                </div>
            </div>
        )
    }

}
