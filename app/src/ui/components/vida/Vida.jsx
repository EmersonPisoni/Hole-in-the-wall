import React, { Component } from 'react'

import './Vida.css'

export class Vida extends Component {

    render() {
        const vidas = [];

        for (let i = 0; i < this.props.vidas; i++) {
            vidas[i] = <div className="coracao-item" ></div>;
        }

        return (
            vidas.map((vida) => {
                return vida;
            })
        )
    }

}
