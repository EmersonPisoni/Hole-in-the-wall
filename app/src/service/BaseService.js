export class BaseService {

    constructor() {
        this.baseUrl = process.env.REACT_APP_API_URL
    }

}

export default BaseService
