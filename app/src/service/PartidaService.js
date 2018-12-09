import axios from 'axios'
import BaseService from './BaseService'
import UsuarioService from './UsuarioService'

export class PartidaService extends BaseService {

    constructor() {
        super()

        this.usuarioService = new UsuarioService()
    }

    iniciar(dificuldade) {
        const token = this.usuarioService.getToken()

        const dados = {
            dificuldade
        }

        return axios.post(`${this.baseUrl}partidas`, dados, {
            headers: {
                'Authorization': `${token}`
            }
        })
    }

    getRanking() {
        const token = this.usuarioService.getToken()

        return axios.get(`${this.baseUrl}partidas`, {
            headers: {
                'Authorization': `${token}`
            }
        })
    }

}

export default PartidaService;
