import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Testing GetModel End Point"
    request {
        method GET()
        url("/api/v1/service-template/model") {
        }
    }
    response {
        body(file("modelresponse.json"))
        status 200
    }
}