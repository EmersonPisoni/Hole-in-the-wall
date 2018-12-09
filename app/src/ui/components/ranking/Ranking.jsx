import React, { Component } from 'react'
import { PartidaService } from './../../../service'
import { Lugar1, Lugar2, Lugar3, Lugar4, Lugar5, Lugar6, Lugar7, Lugar8, Lugar9, Lugar10 } from './../../../assets'

import './Ranking.css'

export class Ranking extends Component {

    constructor(props) {
        super(props)

        this.state = {
            imagens: [
                Lugar1,
                Lugar2,
                Lugar3,
                Lugar4,
                Lugar5,
                Lugar6,
                Lugar7,
                Lugar8,
                Lugar9,
                Lugar10
            ],
            ranking: []
        }
    }

    componentDidMount() {
        this.partidaService = new PartidaService()
        this.partidaService.getRanking().then((response) => {
            this.setState({
                ranking: response.data.content
            })
        })
    }

    renderRanking = () => {
        return this.state.ranking.map((item, indice) => {
            return (
                <tr className="ranking-posicao">
                    <td className="ranking-img-posicao"><img src={this.state.imagens[indice]} alt="posição no ranking" /></td>
                    <td className="ranking-nome">{item.apelido}</td>
                    <td className="ranking-pontos">{item.pontos}pts</td>
                </tr>
            )
        })
    }

    render() {
        return (
            <table className={`${this.props.classeTamanho}`} id={this.props.id}>
                {this.props.children}
                {this.renderRanking()}
            </table>
        )
    }

}
